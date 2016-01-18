package com.xiaojiang.crawler.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.mysql.jdbc.ResultSetMetaData;

/**
 * 
 * @author gehouse
 *
 */
public class CodeMain {
	
	private static final String lineEnd = "\n";
	
	private static final String lineTable = "\t";
	
	private static void run(){
		
		CodeProperties prop = loadProperties();
		
		String jdbcUrl = prop.getJdbcUrl();
		
		Map<String, String> columns = getColumns(prop.getJdbcDriver(),
												jdbcUrl,
												 prop.getJdbcUsername(),
												 prop.getJdbcPassword(),
				 								 prop.getTableName());
		
		String db = getDb(jdbcUrl);
		
		generateBean(db, prop.getTableName(), prop.getJavaPackage(), columns);
		
		generateVoQuery(db, prop.getTableName(), prop.getJavaPackage(), columns);
	}
	
	private static String getDb(String jdbcUrl){
		
		String[] strs = jdbcUrl.split("/");
		
		String str = strs[strs.length-1];
		
		return str.substring(0, str.indexOf("?"));
	}
	
	private static void generateBean(String db, String tableName, String javaPackage, Map<String, String> columns){
		
		if(columns == null)
			return;
		
		String javaClassName = getJavaClassName(tableName);
		
		StringBuffer buf = new StringBuffer();
		buf.append("package ").append(javaPackage).append(".bean").append(";").append(lineEnd).append(lineEnd);
		
		buf.append("import org.aspectj.lang.annotation.DeclareAnnotation;").append(lineEnd);
		buf.append("import com.xiaojiang.bean.BeanBase;").append(";").append(lineEnd).append(lineEnd);
		
		buf.append("public class ").append(javaClassName).append(" implements BeanBase ").append("{").append(lineEnd).append(lineEnd);
		
		StringBuffer methodBuf = new StringBuffer();
		
		StringBuffer toInsertSqlMethodBuf = new StringBuffer();
		toInsertSqlMethodBuf.append(lineTable).append("@Override").append(lineEnd);
		toInsertSqlMethodBuf.append(lineTable).append("public String toInsertSql()").append("{").append(lineEnd);
		toInsertSqlMethodBuf.append(lineTable).append(lineTable).append("String sql=\"insert into ").append(tableName).append(" (\"+").append(lineEnd);
		
		StringBuffer toInsertSqlValuesBuf = new StringBuffer();
		toInsertSqlValuesBuf.append(lineTable).append(lineTable).append("\"values (\";").append(lineEnd);
		
		StringBuffer toUpdateSqlMethodBuf = new StringBuffer();
		toUpdateSqlMethodBuf.append(lineTable).append("@Override").append(lineEnd);
		toUpdateSqlMethodBuf.append(lineTable).append("public String toUpdateSql()").append("{").append(lineEnd);
		toUpdateSqlMethodBuf.append(lineTable).append(lineTable).append("String sql=\"update ").append(tableName).append(" set \";").append(lineEnd);
		
		Iterator<String> it = columns.keySet().iterator();
		
		while(it.hasNext()){
			
			String colName = it.next();
			
			String fielType = columns.get(colName);
			
			String fieldName = getJavaFieldName(colName);
			
			buf.append(lineTable).append("@DeclareAnnotation(\"").append(colName).append("\")").append(lineEnd);
			buf.append(lineTable).append("private ").append(fielType).append(" ").append(fieldName).append(";").append(lineEnd).append(lineEnd);
			
			methodBuf.append(lineTable).append("public void set").append(uppercase(fieldName)).append("(").append("final ").append(fielType).append(" ").append(fieldName).append("){").append(lineEnd);
			methodBuf.append(lineTable).append(lineTable).append("this.").append(fieldName).append(" = ").append(fieldName).append(";").append(lineEnd);
			methodBuf.append(lineTable).append("}").append(lineEnd).append(lineEnd);
			
			if(colName.equals("id"))
				methodBuf.append(lineTable).append("@Override").append(lineEnd);
			
			methodBuf.append(lineTable).append("public ").append(fielType).append(" get").append(uppercase(fieldName)).append("(){").append(lineEnd);
			methodBuf.append(lineTable).append(lineTable).append("return this.").append(fieldName).append(";").append(lineEnd);
			methodBuf.append(lineTable).append("}").append(lineEnd).append(lineEnd);
			
			if(colName.equals("id"))
				continue;
			
			toInsertSqlMethodBuf.append(lineTable).append(lineTable).append("\"").append(colName);
			
			if(it.hasNext())
				toInsertSqlMethodBuf.append(",");
			
			toInsertSqlMethodBuf.append("\"+").append(lineEnd);
			
			toInsertSqlValuesBuf.append(lineTable).append(lineTable);
			toInsertSqlValuesBuf.append("if(this.get").append(uppercase(fieldName)).append("() == null){").append(lineEnd);
			toInsertSqlValuesBuf.append(lineTable).append(lineTable).append(lineTable);
			toInsertSqlValuesBuf.append("sql += ");
			
			if(fielType.equals("String"))
				toInsertSqlValuesBuf.append("\"'\"+");
			
			toInsertSqlValuesBuf.append("this.get").append(uppercase(fieldName)).append("()");
			
			if(fielType.equals("String"))
				toInsertSqlValuesBuf.append("+\"'\"");
			
			if(it.hasNext())
				toInsertSqlValuesBuf.append("+\",\"");
			
			toInsertSqlValuesBuf.append(";").append(lineEnd);
			
			toInsertSqlValuesBuf.append(lineTable).append(lineTable).append("}else{").append(lineEnd);
			
			toInsertSqlValuesBuf.append(lineTable).append(lineTable).append(lineTable);
			toInsertSqlValuesBuf.append("sql += ");
			
			if(fielType.equals("String"))
				toInsertSqlValuesBuf.append("\"'\"+");
			
			toInsertSqlValuesBuf.append("this.get").append(uppercase(fieldName)).append("()");
			
			if(fielType.equals("String"))
				toInsertSqlValuesBuf.append(".replaceAll(\"'\", \"\")").append("+\"'\"");
			
			if(it.hasNext())
				toInsertSqlValuesBuf.append("+\",\"");
			
			toInsertSqlValuesBuf.append(";").append(lineEnd);
			
			toInsertSqlValuesBuf.append(lineTable).append(lineTable).append("}").append(lineEnd);
			
			toUpdateSqlMethodBuf.append(lineTable).append(lineTable).append("if(this.get").append(uppercase(fieldName)).append("() != null) {").append(lineEnd);
			
			toUpdateSqlMethodBuf.append(lineTable).append(lineTable).append(lineTable).append("sql += ").append("\"").append(colName).append("=");
			
			if(fielType.equals("String"))
				toUpdateSqlMethodBuf.append("'");
			
			toUpdateSqlMethodBuf.append("\"").append("+ ");
			
			toUpdateSqlMethodBuf.append("this.get").append(uppercase(fieldName)).append("()");
		
			if(fielType.equals("String"))
				toUpdateSqlMethodBuf.append(".replaceAll(\"'\", \"\")").append("+\"'\"");
			
			if(it.hasNext())
				toUpdateSqlMethodBuf.append("+\",\"");
			
			toUpdateSqlMethodBuf.append(";").append(lineEnd);
		
			toUpdateSqlMethodBuf.append(lineTable).append(lineTable).append("}").append(lineEnd);
			
		}
		
		toInsertSqlMethodBuf.append(lineTable).append(lineTable).append("\") \"+").append(lineEnd);
		
		toInsertSqlValuesBuf.append(lineTable).append(lineTable).append("sql += \")\";").append(lineEnd);
		
		toInsertSqlValuesBuf.append(lineTable).append(lineTable).append("return sql;").append(lineEnd);
		
		toInsertSqlValuesBuf.append(lineTable).append("}").append(lineEnd);
		
		toUpdateSqlMethodBuf.append(lineTable).append(lineTable).append("if(sql.endsWith(\",\")){").append(lineEnd);
		toUpdateSqlMethodBuf.append(lineTable).append(lineTable).append(lineTable).append("sql = sql.substring(0, sql.length()-1);").append(lineEnd);
		toUpdateSqlMethodBuf.append(lineTable).append(lineTable).append("}").append(lineEnd);
		toUpdateSqlMethodBuf.append(lineTable).append(lineTable).append("sql += \" where id=\"+ ").append("this.getId()").append(";").append(lineEnd);
		
		toUpdateSqlMethodBuf.append(lineTable).append(lineTable).append("return sql;").append(lineEnd);
		
		toUpdateSqlMethodBuf.append(lineTable).append("}").append(lineEnd);
		
		StringBuffer dbMethodBuf = new StringBuffer();
		dbMethodBuf.append(lineTable).append("@Override").append(lineEnd);
		dbMethodBuf.append(lineTable).append("public String getDb()").append("{").append(lineEnd);
		dbMethodBuf.append(lineTable).append(lineTable).append("return ").append("\"").append(db).append("\";").append(lineEnd);
		dbMethodBuf.append(lineTable).append("}").append(lineEnd);
		
		String javaBeanCode = buf.toString() + methodBuf.toString() + toInsertSqlMethodBuf.toString()+ toInsertSqlValuesBuf.toString()+ toUpdateSqlMethodBuf.toString()+ dbMethodBuf.toString()+"}";
		
		String fileName = getPath(javaPackage)+"bean/"+javaClassName+".java";
		
		saveBean(javaBeanCode, fileName);
		
	}
	
	private static void generateVoQuery(String db, String tableName, String javaPackage, Map<String, String> columns){
		
		if(columns == null)
			return;
		
		String javaClassName = getJavaClassName(tableName);
		
		StringBuffer buf = new StringBuffer();
		buf.append("package ").append(javaPackage).append(".query").append(";").append(lineEnd).append(lineEnd);
		
		buf.append("import ").append(javaPackage).append(".bean.").append(javaClassName).append(";").append(lineEnd);
		buf.append("import com.xiaojiang.util.QueryBase;").append(";").append(lineEnd).append(lineEnd);
		
		buf.append("public class ").append(javaClassName).append("Query").append(" extends QueryBase ").append("{").append(lineEnd).append(lineEnd);
		
		StringBuffer methodBuf = new StringBuffer();
		
		StringBuffer toQuerySqlMethodBuf = new StringBuffer();
		toQuerySqlMethodBuf.append(lineTable).append("@Override").append(lineEnd);
		toQuerySqlMethodBuf.append(lineTable).append("public String toQuerySql()").append("{").append(lineEnd);
		toQuerySqlMethodBuf.append(lineTable).append(lineTable).append("String sql=\"select ");
		
		Iterator<String> it = columns.keySet().iterator();
		
		while(it.hasNext()){
			
			String colName = it.next();
			
			toQuerySqlMethodBuf.append(colName);
			
			if(it.hasNext())
				toQuerySqlMethodBuf.append(", ");
		}
		
		toQuerySqlMethodBuf.append(" from ").append(tableName).append(" where 1=1\";").append(lineEnd).append(lineEnd);
		
		StringBuffer toCountSqlMethodBuf = new StringBuffer();
		toCountSqlMethodBuf.append(lineTable).append("@Override").append(lineEnd);
		toCountSqlMethodBuf.append(lineTable).append("public String toCountSql()").append("{").append(lineEnd);
		toCountSqlMethodBuf.append(lineTable).append(lineTable).append("String sql=\"select count(*) from ").append(tableName).append(" where 1=1\";").append(lineEnd).append(lineEnd);
		
		it = columns.keySet().iterator();
		
		while(it.hasNext()){
			
			String colName = it.next();
			
			String fielType = columns.get(colName);
			
			String fieldName = getJavaFieldName(colName);
			
			buf.append(lineTable).append("private ").append(fielType).append(" ").append(fieldName).append(";").append(lineEnd).append(lineEnd);
			
			methodBuf.append(lineTable).append("public void set").append(uppercase(fieldName)).append("(").append("final ").append(fielType).append(" ").append(fieldName).append("){").append(lineEnd);
			methodBuf.append(lineTable).append(lineTable).append("this.").append(fieldName).append(" = ").append(fieldName).append(";").append(lineEnd);
			methodBuf.append(lineTable).append("}").append(lineEnd).append(lineEnd);
			
			methodBuf.append(lineTable).append("public ").append(fielType).append(" get").append(uppercase(fieldName)).append("(){").append(lineEnd);
			methodBuf.append(lineTable).append(lineTable).append("return this.").append(fieldName).append(";").append(lineEnd);
			methodBuf.append(lineTable).append("}").append(lineEnd).append(lineEnd);
			
			{
				toQuerySqlMethodBuf.append(lineTable).append(lineTable).append("if(this.get").append(uppercase(fieldName)).append("() != null)").append(lineEnd);
				toQuerySqlMethodBuf.append(lineTable).append(lineTable).append(lineTable).append("sql += \" and ").append(colName).append("=");
				
				if(fielType.equals("String"))
					toQuerySqlMethodBuf.append("'");
				
				toQuerySqlMethodBuf.append("\"+ this.get").append(uppercase(fieldName)).append("()");
				
				if(fielType.equals("String"))
					toQuerySqlMethodBuf.append("+\"").append("'").append("\"");
				
				toQuerySqlMethodBuf.append(";").append(lineEnd);
			}
			{
				toCountSqlMethodBuf.append(lineTable).append(lineTable).append("if(this.get").append(uppercase(fieldName)).append("() != null)").append(lineEnd);
				toCountSqlMethodBuf.append(lineTable).append(lineTable).append(lineTable).append("sql += \" and ").append(colName).append("=");
				
				if(fielType.equals("String"))
					toCountSqlMethodBuf.append("'");
				
				toCountSqlMethodBuf.append("\"+ this.get").append(uppercase(fieldName)).append("()");
				
				if(fielType.equals("String"))
					toCountSqlMethodBuf.append("+\"").append("'").append("\"");
				
				toCountSqlMethodBuf.append(";").append(lineEnd);
			}
			
		}
		
		{
			
			toQuerySqlMethodBuf.append(lineTable).append(lineTable).append("if(this.getCustomQueryCondotion() != null)").append(lineEnd);
			toQuerySqlMethodBuf.append(lineTable).append(lineTable).append(lineTable)
						  .append("sql += \" and \"+ this.getCustomQueryCondotion();").append(lineEnd);

			toQuerySqlMethodBuf.append(lineTable).append(lineTable).append("if(this.getSortColumn() != null)").append(lineEnd);
			toQuerySqlMethodBuf.append(lineTable).append(lineTable).append(lineTable)
						  .append("sql += \" order by \"+").append("this.getSortColumn() +\"").append(" ")
						  .append("\"+ this.getSortBy()").append(";").append(lineEnd);
			
			toQuerySqlMethodBuf.append(lineTable).append(lineTable).append("if(this.getLimit() != null)").append(lineEnd);
			toQuerySqlMethodBuf.append(lineTable).append(lineTable).append(lineTable)
						  .append("sql += \" limit \"+").append("this.getStart() +\"").append(",")
						  .append("\"+ this.getLimit()").append(";").append(lineEnd);
			
			toQuerySqlMethodBuf.append(lineTable).append(lineTable).append("return sql;").append(lineEnd);
			toQuerySqlMethodBuf.append(lineTable).append("}").append(lineEnd);
		}
		
		{
			
			toCountSqlMethodBuf.append(lineTable).append(lineTable).append("if(this.getCustomQueryCondotion() != null)").append(lineEnd);
			toCountSqlMethodBuf.append(lineTable).append(lineTable).append(lineTable)
						  .append("sql += \" and \"+ this.getCustomQueryCondotion();").append(lineEnd);

			toCountSqlMethodBuf.append(lineTable).append(lineTable).append("return sql;").append(lineEnd);
			toCountSqlMethodBuf.append(lineTable).append("}").append(lineEnd);
		}
		
		StringBuffer getResultClassMethodBuf = new StringBuffer();
		getResultClassMethodBuf.append(lineTable).append("@Override").append(lineEnd);
		getResultClassMethodBuf.append(lineTable).append("public Class<?> getResultClass()").append("{").append(lineEnd);
		getResultClassMethodBuf.append(lineTable).append(lineTable).append("return ").append(javaClassName).append(".class;").append(lineEnd);
		getResultClassMethodBuf.append(lineTable).append("}").append(lineEnd);
		
		StringBuffer dbMethodBuf = new StringBuffer();
		dbMethodBuf.append(lineTable).append("@Override").append(lineEnd);
		dbMethodBuf.append(lineTable).append("public String getDb()").append("{").append(lineEnd);
		dbMethodBuf.append(lineTable).append(lineTable).append("return ").append("\"").append(db).append("\";").append(lineEnd);
		dbMethodBuf.append(lineTable).append("}").append(lineEnd);
		
		
		String javaBeanCode = buf.toString() + methodBuf.toString() + toQuerySqlMethodBuf.toString() + toCountSqlMethodBuf.toString() + getResultClassMethodBuf.toString() + dbMethodBuf.toString() + "}";
		
		String fileName = getPath(javaPackage)+"query/"+javaClassName+"Query.java";
		
		saveBean(javaBeanCode, fileName);
		
	}
	
	private static void saveBean(String javaBeanCode, String fileName){
		
		
		try {
			
			File file = new File(fileName);
			
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			
			bos.write(javaBeanCode.getBytes("utf-8"));
			bos.flush();
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static CodeProperties loadProperties(){
		Properties prop = new Properties();   
        InputStream in = Object.class.getResourceAsStream("/codegenerate.properties");   
        try {   
            prop.load(in);
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        CodeProperties cp = new CodeProperties();
        cp.setJdbcDriver(prop.getProperty("jdbc.driver"));
        cp.setJdbcUrl(prop.getProperty("jdbc.url"));
        cp.setJdbcUsername(prop.getProperty("jdbc.username"));
        cp.setJdbcPassword(prop.getProperty("jdbc.password"));
        cp.setTableName(prop.getProperty("table.name"));
        cp.setJavaPackage(prop.getProperty("java.package"));
        
        return cp;
	}

	private static Map<String, String> getColumns(String jdbcDriver, String jdbcUrl, String jdbcUsername, String jdbcPassword, String tableName){
		
		Connection conn = null;
		
		try {
			
			Class.forName(jdbcDriver);
			
			conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
			
			PreparedStatement stmt = conn.prepareStatement("select * from "+tableName+" limit 0,1");
			
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
			
			int col = meta.getColumnCount();
			
			Map<String, String> map = new LinkedHashMap<String, String>();
			
			for(int i=1;i<=col;i++){
				String colName = meta.getColumnName(i);
				int colType = meta.getColumnType(i);
				
				if(colName.equalsIgnoreCase("state")){
					System.out.println();
				}
				map.put(colName, formatColType(colType));
			}
			
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private static String getJavaClassName(String tableName){
		
		String s[] = tableName.split("_");
		
		String javaClassName = "";
		
		for(int i=0;i<s.length;i++){
			javaClassName += uppercase(s[i]);
		}
		
		return javaClassName;
	}
	
	private static String getJavaFieldName(String columnName){
		
		String s[] = columnName.split("_");
		
		String javaFieldName = s[0];
		
		for(int i=1;i<s.length;i++){
			javaFieldName += uppercase(s[i]);
		}
		
		return javaFieldName;
	}
	
	private static String uppercase(String s){
		return s.substring(0, 1).toUpperCase()+s.substring(1, s.length());
	}
	
	private static String formatColType(int colType){
	
		  String ret="";
		  switch(colType){
		   case(-1):ret="String";break;
		   case(-5):ret="Long";break;
		   case(-6):ret="Integer";break;
		   case(-7):ret="Boolean";break;
		   case(1):ret="String";break;
		   case(4):ret="Integer";break;
		   case(6):ret="Float";break;
		   case(7):ret="Float";break;
		   case(8):ret="Double";break;
		   case(12):ret="String";break;
		   case(91):ret="Date";break;
		   default:ret="String";
		  }
		  return ret;
		
	}
	
	private static String getPath(String javaPackage){
		 String className = CodeMain.class.getName();
		 String classNamePath = className.replace(".", "/") + ".class";
		 String path = CodeMain.class.getClassLoader().getResource(classNamePath).getFile();
		 
		 path = path.replace("target/classes", "src");
		 
		 path = path.substring(1, path.indexOf("com"));
		 
		 return path + javaPackage.replace(".", "/")+"/";
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		run();

	}

}
