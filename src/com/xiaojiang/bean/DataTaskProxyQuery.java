package com.xiaojiang.bean;

import com.xiaojiang.util.QueryBase;


public class DataTaskProxyQuery extends QueryBase{

	private Long id;

	private String address;

	private String port;

	private String location;

	private Boolean isAnonymous;

	private String protocol;

	private Double speed;

	private Boolean isBlack;

	private Double connectTime;

	private Long validateDate;

	private Long succNum;

	private Long errNum;

	private String name;

	private String password;

	private Long creationDate;

	private Long modificationDate;

	public void setId(final Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setAddress(final String address){
		this.address = address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setPort(final String port){
		this.port = port;
	}

	public String getPort(){
		return this.port;
	}

	public void setLocation(final String location){
		this.location = location;
	}

	public String getLocation(){
		return this.location;
	}

	public void setIsAnonymous(final Boolean isAnonymous){
		this.isAnonymous = isAnonymous;
	}

	public Boolean getIsAnonymous(){
		return this.isAnonymous;
	}

	public void setProtocol(final String protocol){
		this.protocol = protocol;
	}

	public String getProtocol(){
		return this.protocol;
	}

	public void setSpeed(final Double speed){
		this.speed = speed;
	}

	public Double getSpeed(){
		return this.speed;
	}

	public void setIsBlack(final Boolean isBlack){
		this.isBlack = isBlack;
	}

	public Boolean getIsBlack(){
		return this.isBlack;
	}

	public void setConnectTime(final Double connectTime){
		this.connectTime = connectTime;
	}

	public Double getConnectTime(){
		return this.connectTime;
	}

	public void setValidateDate(final Long validateDate){
		this.validateDate = validateDate;
	}

	public Long getValidateDate(){
		return this.validateDate;
	}

	public void setSuccNum(final Long succNum){
		this.succNum = succNum;
	}

	public Long getSuccNum(){
		return this.succNum;
	}

	public void setErrNum(final Long errNum){
		this.errNum = errNum;
	}

	public Long getErrNum(){
		return this.errNum;
	}

	public void setName(final String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setPassword(final String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
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
	public String toQuerySql(){
		String sql="select id, address, port, location, is_anonymous, protocol, speed, is_black, connect_time, validate_date, succ_num, err_num, name, password, creation_date, modification_date from data_task_proxy where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getAddress() != null)
			sql += " and address='"+ this.getAddress()+"'";
		if(this.getPort() != null)
			sql += " and port='"+ this.getPort()+"'";
		if(this.getLocation() != null)
			sql += " and location='"+ this.getLocation()+"'";
		if(this.getIsAnonymous() != null)
			sql += " and is_anonymous="+ this.getIsAnonymous();
		if(this.getProtocol() != null)
			sql += " and protocol='"+ this.getProtocol()+"'";
		if(this.getSpeed() != null)
			sql += " and speed="+ this.getSpeed();
		if(this.getIsBlack() != null)
			sql += " and is_black="+ this.getIsBlack();
		if(this.getConnectTime() != null)
			sql += " and connect_time="+ this.getConnectTime();
		if(this.getValidateDate() != null)
			sql += " and validate_date="+ this.getValidateDate();
		if(this.getSuccNum() != null)
			sql += " and succ_num="+ this.getSuccNum();
		if(this.getErrNum() != null)
			sql += " and err_num="+ this.getErrNum();
		if(this.getName() != null)
			sql += " and name='"+ this.getName()+"'";
		if(this.getPassword() != null)
			sql += " and password='"+ this.getPassword()+"'";
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
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
		String sql="select count(*) from data_task_proxy where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getAddress() != null)
			sql += " and address='"+ this.getAddress()+"'";
		if(this.getPort() != null)
			sql += " and port='"+ this.getPort()+"'";
		if(this.getLocation() != null)
			sql += " and location='"+ this.getLocation()+"'";
		if(this.getIsAnonymous() != null)
			sql += " and is_anonymous="+ this.getIsAnonymous();
		if(this.getProtocol() != null)
			sql += " and protocol='"+ this.getProtocol()+"'";
		if(this.getSpeed() != null)
			sql += " and speed="+ this.getSpeed();
		if(this.getIsBlack() != null)
			sql += " and is_black="+ this.getIsBlack();
		if(this.getConnectTime() != null)
			sql += " and connect_time="+ this.getConnectTime();
		if(this.getValidateDate() != null)
			sql += " and validate_date="+ this.getValidateDate();
		if(this.getSuccNum() != null)
			sql += " and succ_num="+ this.getSuccNum();
		if(this.getErrNum() != null)
			sql += " and err_num="+ this.getErrNum();
		if(this.getName() != null)
			sql += " and name='"+ this.getName()+"'";
		if(this.getPassword() != null)
			sql += " and password='"+ this.getPassword()+"'";
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return DataTaskProxy.class;
	}
	@Override
	public String getDb(){
		return "global";
	}
}
