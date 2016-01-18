package com.xiaojiang.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;



public class DataTaskProxy implements BeanBase{
	
	@DeclareAnnotation("id")
	private Long id;
	//ip地址
	@DeclareAnnotation("address")
	private String address;
	//端口号
	@DeclareAnnotation("port")
	private String port;
	//位置
	@DeclareAnnotation("location")
	private String location;
	//是否高匿
	@DeclareAnnotation("is_anonymous")
	private Boolean isAnonymous;
	//协议类型
	@DeclareAnnotation("protocol")
	private String protocol;
	//速度
	@DeclareAnnotation("speed")
	private Double speed;
	//是否加入黑名单，true-是，false-否
	@DeclareAnnotation("is_black")
	private Boolean isBlack;
	//连接时间
	@DeclareAnnotation("connect_time")
	private Double connectTime;
	//验证时间
	@DeclareAnnotation("validate_date")
	private Long validateDate;
	//成功次数
	@DeclareAnnotation("succ_num")
	private Long succNum;
	//失败次数
	@DeclareAnnotation("err_num")
	private Long errNum;
	@DeclareAnnotation("name")
	private String name;
	@DeclareAnnotation("password")
	private String password;
	//创建时间
	@DeclareAnnotation("creation_date")
	private Long creationDate;
	//修改时间
	@DeclareAnnotation("modification_date")
	private Long modificationDate;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getIsAnonymous() {
		return isAnonymous;
	}
	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Boolean getIsBlack() {
		return isBlack;
	}
	public void setIsBlack(Boolean isBlack) {
		this.isBlack = isBlack;
	}
	public Double getConnectTime() {
		return connectTime;
	}
	public void setConnectTime(Double connectTime) {
		this.connectTime = connectTime;
	}
	public Long getValidateDate() {
		return validateDate;
	}
	public void setValidateDate(Long validateDate) {
		this.validateDate = validateDate;
	}
	public Long getSuccNum() {
		return succNum;
	}
	public void setSuccNum(Long succNum) {
		this.succNum = succNum;
	}
	public Long getErrNum() {
		return errNum;
	}
	public void setErrNum(Long errNum) {
		this.errNum = errNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}
	public Long getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Long modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	
	@Override
	public String getDb() {
		return "global";
	}
	@Override
	public String toInsertSql() {
		String sql="insert into data_task_proxy ("+
				"address,"+
				"port,"+
				"location,"+
				"is_anonymous,"+
				"protocol,"+
				"speed,"+
				"is_black,"+
				"connect_time,"+
				"validate_date,"+
				"succ_num,"+
				"err_num,"+
				"name,"+
				"password,"+
				"creation_date,"+
				"modification_date"+
				") "+
				"values (";
				if(this.getAddress() == null){
					sql += "'"+this.getAddress()+"'"+",";
				}else{
					sql += "'"+this.getAddress().replaceAll("'", "")+"'"+",";
				}
				if(this.getPort() == null){
					sql += "'"+this.getPort()+"'"+",";
				}else{
					sql += "'"+this.getPort().replaceAll("'", "")+"'"+",";
				}
				if(this.getLocation() == null){
					sql += "'"+this.getLocation()+"'"+",";
				}else{
					sql += "'"+this.getLocation().replaceAll("'", "")+"'"+",";
				}
				if(this.getIsAnonymous() == null){
					sql += this.getIsAnonymous()+",";
				}else{
					sql += this.getIsAnonymous()+",";
				}
				if(this.getProtocol() == null){
					sql += "'"+this.getProtocol()+"'"+",";
				}else{
					sql += "'"+this.getProtocol().replaceAll("'", "")+"'"+",";
				}
				if(this.getSpeed() == null){
					sql += this.getSpeed()+",";
				}else{
					sql += this.getSpeed()+",";
				}
				if(this.getIsBlack() == null){
					sql += this.getIsBlack()+",";
				}else{
					sql += this.getIsBlack()+",";
				}
				if(this.getConnectTime() == null){
					sql += this.getConnectTime()+",";
				}else{
					sql += this.getConnectTime()+",";
				}
				if(this.getValidateDate() == null){
					sql += this.getValidateDate()+",";
				}else{
					sql += this.getValidateDate()+",";
				}
				if(this.getSuccNum() == null){
					sql += this.getSuccNum()+",";
				}else{
					sql += this.getSuccNum()+",";
				}
				if(this.getErrNum() == null){
					sql += this.getErrNum()+",";
				}else{
					sql += this.getErrNum()+",";
				}
				if(this.getName() == null){
					sql += "'"+this.getName()+"'"+",";
				}else{
					sql += "'"+this.getName().replaceAll("'", "")+"'"+",";
				}
				if(this.getPassword() == null){
					sql += "'"+this.getPassword()+"'"+",";
				}else{
					sql += "'"+this.getPassword().replaceAll("'", "")+"'"+",";
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
	public String toUpdateSql() {
		String sql="update data_task_proxy set ";
		if(this.getAddress() != null) {
			sql += "address='"+ this.getAddress().replaceAll("'", "")+"'"+",";
		}
		if(this.getPort() != null) {
			sql += "port='"+ this.getPort().replaceAll("'", "")+"'"+",";
		}
		if(this.getLocation() != null) {
			sql += "location='"+ this.getLocation().replaceAll("'", "")+"'"+",";
		}
		if(this.getIsAnonymous() != null) {
			sql += "is_anonymous="+ this.getIsAnonymous()+",";
		}
		if(this.getProtocol() != null) {
			sql += "protocol='"+ this.getProtocol().replaceAll("'", "")+"'"+",";
		}
		if(this.getSpeed() != null) {
			sql += "speed="+ this.getSpeed()+",";
		}
		if(this.getIsBlack() != null) {
			sql += "is_black="+ this.getIsBlack()+",";
		}
		if(this.getConnectTime() != null) {
			sql += "connect_time="+ this.getConnectTime()+",";
		}
		if(this.getValidateDate() != null) {
			sql += "validate_date="+ this.getValidateDate()+",";
		}
		if(this.getSuccNum() != null) {
			sql += "succ_num="+ this.getSuccNum()+",";
		}
		if(this.getErrNum() != null) {
			sql += "err_num="+ this.getErrNum()+",";
		}
		if(this.getName() != null) {
			sql += "name='"+ this.getName().replaceAll("'", "")+"'"+",";
		}
		if(this.getPassword() != null) {
			sql += "password='"+ this.getPassword().replaceAll("'", "")+"'"+",";
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
	
}
