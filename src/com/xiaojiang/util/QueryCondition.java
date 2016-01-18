package com.xiaojiang.util;

public interface QueryCondition {
	
	public String getDb();
	
	public String toQuerySql();
	
	public String toCountSql();
	
	public Class<?> getResultClass();
}
