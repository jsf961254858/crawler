package com.xiaojiang.bean;

import com.xiaojiang.util.QueryBase;


public class RunningLogQuery extends QueryBase {

	private Long id;

	private Long spiderid;

	private Long scheduleid;

	private String reason;
	
	private String runningUrlInfo;
	
	private String runningHouseInfo;
	
	private String cleanInfo;

	private String url;

	private Long creationDate;

	public void setId(final Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setSpiderid(final Long spiderid){
		this.spiderid = spiderid;
	}

	public Long getSpiderid(){
		return this.spiderid;
	}

	public void setScheduleid(final Long scheduleid){
		this.scheduleid = scheduleid;
	}

	public Long getScheduleid(){
		return this.scheduleid;
	}

	public void setReason(final String reason){
		this.reason = reason;
	}

	public String getReason(){
		return this.reason;
	}

	public void setUrl(final String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public void setCreationDate(final Long creationDate){
		this.creationDate = creationDate;
	}

	public Long getCreationDate(){
		return this.creationDate;
	}

	@Override
	public String toQuerySql(){
		String sql="select id, spiderid, scheduleid, reason, runningUrlInfo, runningHouseInfo,  cleanInfo, url, creation_date from running_log where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getSpiderid() != null)
			sql += " and spiderid="+ this.getSpiderid();
		if(this.getScheduleid() != null)
			sql += " and scheduleid="+ this.getScheduleid();
		if(this.getReason() != null)
			sql += " and reason='"+ this.getReason()+"'";
		if(this.getRunningUrlInfo() != null)
			sql += " and runningUrlInfo='"+ this.getRunningUrlInfo()+"'";
		if(this.getRunningHouseInfo() != null)
			sql += " and runningHouseInfo='"+ this.getRunningHouseInfo()+"'";
		if(this.getCleanInfo() != null)
			sql += " and cleanInfo='"+ this.getCleanInfo()+"'";
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		if(this.getSortColumn() != null)
			sql += " order by "+this.getSortColumn() +" "+ this.getSortBy();
		if(this.getLimit() != null)
			sql += " limit "+this.getStart() +","+ this.getLimit();
		return sql;
	}
	@Override
	public String toCountSql(){
		String sql="select count(*) from running_log where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getSpiderid() != null)
			sql += " and spiderid="+ this.getSpiderid();
		if(this.getScheduleid() != null)
			sql += " and scheduleid="+ this.getScheduleid();
		if(this.getReason() != null)
			sql += " and reason='"+ this.getReason()+"'";
		if(this.getRunningUrlInfo() != null)
			sql += " and runningUrlInfo='"+ this.getRunningUrlInfo()+"'";
		if(this.getRunningHouseInfo() != null)
			sql += " and runningHouseInfo='"+ this.getRunningHouseInfo()+"'";
		if(this.getCleanInfo() != null)
			sql += " and cleanInfo='"+ this.getCleanInfo()+"'";
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return RunningLog.class;
	}
	@Override
	public String getDb(){
		return "log";
	}

	public String getRunningUrlInfo() {
		return runningUrlInfo;
	}

	public void setRunningUrlInfo(String runningUrlInfo) {
		this.runningUrlInfo = runningUrlInfo;
	}

	public String getRunningHouseInfo() {
		return runningHouseInfo;
	}

	public void setRunningHouseInfo(String runningHouseInfo) {
		this.runningHouseInfo = runningHouseInfo;
	}

	public String getCleanInfo() {
		return cleanInfo;
	}

	public void setCleanInfo(String cleanInfo) {
		this.cleanInfo = cleanInfo;
	}
}