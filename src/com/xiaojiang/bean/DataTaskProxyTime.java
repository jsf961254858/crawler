package com.xiaojiang.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;


public class DataTaskProxyTime implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("proxy_id")
	private Long proxyId;

	@DeclareAnnotation("spider_id")
	private Long spiderId;

	@DeclareAnnotation("speed")
	private Long speed;

	@DeclareAnnotation("creation_date")
	private Long creationDate;

	@DeclareAnnotation("modification_date")
	private Long modificationDate;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
	public Long getId(){
		return this.id;
	}

	public void setProxyId(final Long proxyId){
		this.proxyId = proxyId;
	}

	public Long getProxyId(){
		return this.proxyId;
	}

	public void setSpiderId(final Long spiderId){
		this.spiderId = spiderId;
	}

	public Long getSpiderId(){
		return this.spiderId;
	}

	public void setSpeed(final Long speed){
		this.speed = speed;
	}

	public Long getSpeed(){
		return this.speed;
	}

	public void setCreationDate(final Long creationDate){
		this.creationDate = creationDate;
	}

	public Long getCreationDate(){
		return this.creationDate;
	}

	public void setModificationDate(final Long modificationDate){
		this.modificationDate = modificationDate;
	}

	public Long getModificationDate(){
		return this.modificationDate;
	}

	@Override
	public String toInsertSql(){
		String sql="insert into data_task_proxy_time ("+
		"proxy_id,"+
		"spider_id,"+
		"speed,"+
		"creation_date,"+
		"modification_date"+
		") "+
		"values (";
		if(this.getProxyId() == null){
			sql += this.getProxyId()+",";
		}else{
			sql += this.getProxyId()+",";
		}
		if(this.getSpiderId() == null){
			sql += this.getSpiderId()+",";
		}else{
			sql += this.getSpiderId()+",";
		}
		if(this.getSpeed() == null){
			sql += this.getSpeed()+",";
		}else{
			sql += this.getSpeed()+",";
		}
		if(this.getCreationDate() == null){
			sql += this.getCreationDate()+",";
		}else{
			sql += this.getCreationDate()+",";
		}
		if(this.getModificationDate() == null){
			sql += this.getModificationDate();
		}else{
			sql += this.getModificationDate();
		}
		sql += ")";
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update data_task_proxy_time set ";
		if(this.getProxyId() != null) {
			sql += "proxy_id="+ this.getProxyId()+",";
		}
		if(this.getSpiderId() != null) {
			sql += "spider_id="+ this.getSpiderId()+",";
		}
		if(this.getSpeed() != null) {
			sql += "speed="+ this.getSpeed()+",";
		}
		if(this.getCreationDate() != null) {
			sql += "creation_date="+ this.getCreationDate()+",";
		}
		if(this.getModificationDate() != null) {
			sql += "modification_date="+ this.getModificationDate();
		}
		if(sql.endsWith(",")){
			sql = sql.substring(0, sql.length()-1);
		}
		sql += " where id="+ this.getId();
		return sql;
	}
	@Override
	public String getDb(){
		return "global";
	}

}
