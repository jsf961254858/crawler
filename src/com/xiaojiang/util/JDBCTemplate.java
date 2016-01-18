package com.xiaojiang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xiaojiang.bean.BeanBase;


public class JDBCTemplate {

	public static Long save(BeanBase entity){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			
			conn = getConnection(entity.getDb());
			
			stmt = conn.createStatement();

			Long id = entity.getId();
			
			if(id != null && id != 0){
				sql = entity.toUpdateSql();
				stmt.executeUpdate(sql);
				 return id;
			}else{
				sql = entity.toInsertSql();
				
				int row = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				
				rs = stmt.getGeneratedKeys();  

			     if ( rs.next() ) {  
			    	id = rs.getLong(row);  
			        return id;
			    }  
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{

			try {
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(conn != null){conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return null;
	}


	public static Object findOne(QueryCondition con){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql =null;
		
		try {
			
			conn = getConnection(con.getDb());
			
			sql = con.toQuerySql();
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(!rs.next())
				return null;
			
			Object obj = QueryResultFormat.format(rs, con.getResultClass());
			return obj;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(conn != null){conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public static List<?> findList(QueryCondition con){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			
			conn = getConnection(con.getDb());
			
			sql = con.toQuerySql();
			
			stmt = conn.prepareStatement(con.toQuerySql());
			
			rs = stmt.executeQuery();
			
			List objs = new ArrayList();
			
			while(rs.next()){
				objs.add(QueryResultFormat.format(rs, con.getResultClass()));
			}
			
			return objs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(conn != null){conn.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return null;
	}
	
	
public static int findCount(QueryCondition con){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			
			conn = getConnection(con.getDb());
			
			sql = con.toCountSql();
			stmt = conn.prepareStatement(con.toCountSql());
			
			rs = stmt.executeQuery();
			
			if(!rs.next())
				return 0;
			
			return rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(conn != null){conn.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return 0;
	}
	
	public static void updateBySql(String db, String sql){
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			
			conn = getConnection(db);
			
			stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(stmt!=null){stmt.close();}
				if(conn != null){conn.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public static Connection getConnection(String db){
		Connection conn = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/"+db;
			
			conn = DriverManager.getConnection(url, "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/*
	public static void main(String[] args) {
		JDBCTemplate jdbc = new JDBCTemplate();
		Connection conn = jdbc.getConnection("global");
		if(conn == null)
			System.out.println("数据库连接失败");
		else
			System.out.println("数据库连接成功");
	}*/
	
	
}
