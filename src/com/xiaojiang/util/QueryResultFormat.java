package com.xiaojiang.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class QueryResultFormat {
	
	private static final Log log = LogFactory.getLog(QueryResultFormat.class);
	
	public static Object format(ResultSet rs, Class<?> clazz) {
		
		try {
			
			Field[] fields = clazz.getDeclaredFields();
			
			Object obj = clazz.newInstance();
			
			for(int i=0;i<fields.length;i++){
				
				Field f = fields[i];
				
				String fname = f.getName();
				
				Type ftype = f.getGenericType();
				
				String typeStr = ftype.toString();
				
				if(fname.equalsIgnoreCase("serialVersionUID"))
					continue;
				
				Annotation[] annos = f.getDeclaredAnnotations();
				
				if(annos == null || annos.length == 0)
					continue;
				
				String column = annos[0].toString();
				
				column = column.substring(column.lastIndexOf("=")+1, column.length()-1);
				
				Method method = clazz.getDeclaredMethod("set"+uppercase(fname), f.getType());
				
				method.invoke(obj, getValue(rs, typeStr, column));
				
			}
			
			return obj;
		} catch (SecurityException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			
		} catch (InstantiationException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return null;
		
	}
	
	private static Object getValue(ResultSet rs, String ftype, String column) throws SQLException{
		
		Object value = null;
		
		if(ftype.contains("Long"))
			value = rs.getLong(column);
		
		if(ftype.contains("String"))
			value = rs.getString(column);
		
		if(ftype.contains("Integer"))
			value = rs.getInt(column);
		
		if(ftype.contains("Double"))
			value = rs.getDouble(column);
		
		if(ftype.contains("Boolean"))
			value = rs.getBoolean(column);
	
		if(ftype.contains("Float"))
			value = rs.getFloat(column);
	
		return value;
	}
	
	private static String uppercase(String s){
		return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
	}

	
}
