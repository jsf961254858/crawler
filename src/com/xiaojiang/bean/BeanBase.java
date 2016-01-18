package com.xiaojiang.bean;

public interface BeanBase {

	
	public String getDb();
	
	public Long getId();
	
	public String toInsertSql();
	
	public String toUpdateSql();
	
}
