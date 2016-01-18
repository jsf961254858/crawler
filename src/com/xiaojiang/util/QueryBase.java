package com.xiaojiang.util;

public class QueryBase implements QueryCondition {

	private String customQueryCondotion;
	private String sortColumn;
	private String sortBy = "desc";
	private Integer start = 0;
	private Integer limit;
	
	public String getCustomQueryCondotion() {
		return customQueryCondotion;
	}
	public void setCustomQueryCondotion(String customQueryCondotion) {
		this.customQueryCondotion = customQueryCondotion;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	
	@Override
	public String getDb() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toQuerySql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toCountSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getResultClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
