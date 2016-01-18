package com.xiaojiang.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;

public class RunningLog implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("spiderid")
	private Long spiderid;

	@DeclareAnnotation("scheduleid")
	private Long scheduleid;

	@DeclareAnnotation("reason")
	private String reason;

	@DeclareAnnotation("url")
	private String url;

	@DeclareAnnotation("creation_date")
	private Long creationDate;
	
	@DeclareAnnotation("runningUrlInfo")
	private String runningUrlInfo;
	
	@DeclareAnnotation("runningHouseInfo")
	private String runningHouseInfo;
	
	@DeclareAnnotation("cleanInfo")
	private String cleanInfo;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
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
	public String toInsertSql(){
		String sql="insert into running_log ("+
		"spiderid,"+
		"scheduleid,"+
		"reason,"+
		"runningUrlInfo,"+
		"runningHouseInfo,"+
		"cleanInfo,"+
		"url,"+
		"creation_date"+
		") "+
		"values (";
		if(this.getSpiderid() == null){
			sql += this.getSpiderid()+",";
		}else{
			sql += this.getSpiderid()+",";
		}
		if(this.getScheduleid() == null){
			sql += this.getScheduleid()+",";
		}else{
			sql += this.getScheduleid()+",";
		}
		if(this.getReason() == null){
			sql += "'"+this.getReason()+"'"+",";
		}else{
			sql += "'"+this.getReason().replaceAll("'", "")+"'"+",";
		}
		if(this.getRunningUrlInfo() == null){
			sql += "'"+this.getRunningUrlInfo()+"'"+",";
		}else{
			sql += "'"+this.getRunningUrlInfo().replaceAll("'", "")+"'"+",";
		}
		if(this.getRunningHouseInfo() == null){
			sql += "'"+this.getRunningHouseInfo()+"'"+",";
		}else{
			sql += "'"+this.getRunningHouseInfo().replaceAll("'", "")+"'"+",";
		}
		if(this.getCleanInfo() == null){
			sql += "'"+this.getCleanInfo()+"'"+",";
		}else{
			sql += "'"+this.getCleanInfo().replaceAll("'", "")+"'"+",";
		}
		if(this.getUrl() == null){
			sql += "'"+this.getUrl()+"'"+",";
		}else{
			sql += "'"+this.getUrl().replaceAll("'", "")+"'"+",";
		}
		if(this.getCreationDate() == null){
			sql += this.getCreationDate();
		}else{
			sql += this.getCreationDate();
		}
		sql += ")";
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update running_log set ";
		if(this.getSpiderid() != null) {
			sql += "spiderid="+ this.getSpiderid()+",";
		}
		if(this.getScheduleid() != null) {
			sql += "scheduleid="+ this.getScheduleid()+",";
		}
		if(this.getReason() != null) {
			sql += "reason='"+ this.getReason().replaceAll("'", "")+"'"+",";
		}
		if(this.getRunningUrlInfo() != null) {
			sql += "reason='"+ this.getRunningUrlInfo().replaceAll("'", "")+"'"+",";
		}
		if(this.getRunningHouseInfo() != null) {
			sql += "reason='"+ this.getRunningHouseInfo().replaceAll("'", "")+"'"+",";
		}
		if(this.getCleanInfo() != null) {
			sql += "reason='"+ this.getCleanInfo().replaceAll("'", "")+"'"+",";
		}
		if(this.getUrl() != null) {
			sql += "url='"+ this.getUrl().replaceAll("'", "")+"'"+",";
		}
		if(this.getCreationDate() != null) {
			sql += "creation_date="+ this.getCreationDate();
		}
		if(sql.endsWith(",")){
			sql = sql.substring(0, sql.length()-1);
		}
		sql += " where id="+ this.getId();
		return sql;
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