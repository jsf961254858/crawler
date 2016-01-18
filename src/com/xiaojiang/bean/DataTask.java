package com.xiaojiang.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;

public class DataTask implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("userid")
	private Long userid;

	@DeclareAnnotation("groupid")
	private Long groupid;

	@DeclareAnnotation("subtaskurl")
	private String subtaskurl;

	@DeclareAnnotation("tasktype")
	private Integer tasktype;

	@DeclareAnnotation("istoptask")
	private Boolean istoptask;

	@DeclareAnnotation("code")
	private String code;

	@DeclareAnnotation("city")
	private String city;

	@DeclareAnnotation("type")
	private String type;

	@DeclareAnnotation("programname")
	private String programname;

	@DeclareAnnotation("name")
	private String name;

	@DeclareAnnotation("datatype")
	private String datatype;

	@DeclareAnnotation("website")
	private String website;

	@DeclareAnnotation("webpage")
	private String webpage;

	@DeclareAnnotation("start")
	private Integer start;

	@DeclareAnnotation("end")
	private Integer end;

	@DeclareAnnotation("period")
	private Integer period;

	@DeclareAnnotation("sleep_interval")
	private Long sleepInterval;

	@DeclareAnnotation("isproxy")
	private Boolean isproxy;

	@DeclareAnnotation("islive")
	private String islive;

	@DeclareAnnotation("programparams")
	private String programparams;

	@DeclareAnnotation("creation_date")
	private Long creationDate;

	@DeclareAnnotation("modification_date")
	private Long modificationDate;

	@Override
	public String getDb() {
		return "global";
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String toInsertSql() {
		String sql="insert into data_task ("+
				"userid,"+
				"groupid,"+
				"subtaskurl,"+
				"tasktype,"+
				"istoptask,"+
				"code,"+
				"city,"+
				"type,"+
				"programname,"+
				"name,"+
				"datatype,"+
				"website,"+
				"webpage,"+
				"start,"+
				"end,"+
				"period,"+
				"sleep_interval,"+
				"isproxy,"+
				"islive,"+
				"programparams,"+
				"creation_date,"+
				"modification_date"+
				") "+
				"values (";
				if(this.getUserid() == null){
					sql += this.getUserid()+",";
				}else{
					sql += this.getUserid()+",";
				}
				if(this.getGroupid() == null){
					sql += this.getGroupid()+",";
				}else{
					sql += this.getGroupid()+",";
				}
				if(this.getSubtaskurl() == null){
					sql += "'"+this.getSubtaskurl()+"'"+",";
				}else{
					sql += "'"+this.getSubtaskurl().replaceAll("'", "")+"'"+",";
				}
				if(this.getTasktype() == null){
					sql += this.getTasktype()+",";
				}else{
					sql += this.getTasktype()+",";
				}
				if(this.getIstoptask() == null){
					sql += this.getIstoptask()+",";
				}else{
					sql += this.getIstoptask()+",";
				}
				if(this.getCode() == null){
					sql += "'"+this.getCode()+"'"+",";
				}else{
					sql += "'"+this.getCode().replaceAll("'", "")+"'"+",";
				}
				if(this.getCity() == null){
					sql += "'"+this.getCity()+"'"+",";
				}else{
					sql += "'"+this.getCity().replaceAll("'", "")+"'"+",";
				}
				if(this.getType() == null){
					sql += "'"+this.getType()+"'"+",";
				}else{
					sql += "'"+this.getType().replaceAll("'", "")+"'"+",";
				}
				if(this.getProgramname() == null){
					sql += "'"+this.getProgramname()+"'"+",";
				}else{
					sql += "'"+this.getProgramname().replaceAll("'", "")+"'"+",";
				}
				if(this.getName() == null){
					sql += "'"+this.getName()+"'"+",";
				}else{
					sql += "'"+this.getName().replaceAll("'", "")+"'"+",";
				}
				if(this.getDatatype() == null){
					sql += "'"+this.getDatatype()+"'"+",";
				}else{
					sql += "'"+this.getDatatype().replaceAll("'", "")+"'"+",";
				}
				if(this.getWebsite() == null){
					sql += "'"+this.getWebsite()+"'"+",";
				}else{
					sql += "'"+this.getWebsite().replaceAll("'", "")+"'"+",";
				}
				if(this.getWebpage() == null){
					sql += "'"+this.getWebpage()+"'"+",";
				}else{
					sql += "'"+this.getWebpage().replaceAll("'", "")+"'"+",";
				}
				if(this.getStart() == null){
					sql += this.getStart()+",";
				}else{
					sql += this.getStart()+",";
				}
				if(this.getEnd() == null){
					sql += this.getEnd()+",";
				}else{
					sql += this.getEnd()+",";
				}
				if(this.getPeriod() == null){
					sql += this.getPeriod()+",";
				}else{
					sql += this.getPeriod()+",";
				}
				if(this.getSleepInterval() == null){
					sql += this.getSleepInterval()+",";
				}else{
					sql += this.getSleepInterval()+",";
				}
				if(this.getIsproxy() == null){
					sql += this.getIsproxy()+",";
				}else{
					sql += this.getIsproxy()+",";
				}
				if(this.getIslive() == null){
					sql += "'"+this.getIslive()+"'"+",";
				}else{
					sql += "'"+this.getIslive().replaceAll("'", "")+"'"+",";
				}
				if(this.getProgramparams() == null){
					sql += "'"+this.getProgramparams()+"'"+",";
				}else{
					sql += "'"+this.getProgramparams().replaceAll("'", "")+"'"+",";
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
		String sql="update data_task set ";
		if(this.getUserid() != null) {
			sql += "userid="+ this.getUserid()+",";
		}
		if(this.getGroupid() != null) {
			sql += "groupid="+ this.getGroupid()+",";
		}
		if(this.getSubtaskurl() != null) {
			sql += "subtaskurl='"+ this.getSubtaskurl().replaceAll("'", "")+"'"+",";
		}
		if(this.getTasktype() != null) {
			sql += "tasktype="+ this.getTasktype()+",";
		}
		if(this.getIstoptask() != null) {
			sql += "istoptask="+ this.getIstoptask()+",";
		}
		if(this.getCode() != null) {
			sql += "code='"+ this.getCode().replaceAll("'", "")+"'"+",";
		}
		if(this.getCity() != null) {
			sql += "city='"+ this.getCity().replaceAll("'", "")+"'"+",";
		}
		if(this.getType() != null) {
			sql += "type='"+ this.getType().replaceAll("'", "")+"'"+",";
		}
		if(this.getProgramname() != null) {
			sql += "programname='"+ this.getProgramname().replaceAll("'", "")+"'"+",";
		}
		if(this.getName() != null) {
			sql += "name='"+ this.getName().replaceAll("'", "")+"'"+",";
		}
		if(this.getDatatype() != null) {
			sql += "datatype='"+ this.getDatatype().replaceAll("'", "")+"'"+",";
		}
		if(this.getWebsite() != null) {
			sql += "website='"+ this.getWebsite().replaceAll("'", "")+"'"+",";
		}
		if(this.getWebpage() != null) {
			sql += "webpage='"+ this.getWebpage().replaceAll("'", "")+"'"+",";
		}
		if(this.getStart() != null) {
			sql += "start="+ this.getStart()+",";
		}
		if(this.getEnd() != null) {
			sql += "end="+ this.getEnd()+",";
		}
		if(this.getPeriod() != null) {
			sql += "period="+ this.getPeriod()+",";
		}
		if(this.getSleepInterval() != null) {
			sql += "sleep_interval="+ this.getSleepInterval()+",";
		}
		if(this.getIsproxy() != null) {
			sql += "isproxy="+ this.getIsproxy()+",";
		}
		if(this.getIslive() != null) {
			sql += "islive='"+ this.getIslive().replaceAll("'", "")+"'"+",";
		}
		if(this.getProgramparams() != null) {
			sql += "programparams='"+ this.getProgramparams().replaceAll("'", "")+"'"+",";
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

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public String getSubtaskurl() {
		return subtaskurl;
	}

	public void setSubtaskurl(String subtaskurl) {
		this.subtaskurl = subtaskurl;
	}

	public Integer getTasktype() {
		return tasktype;
	}

	public void setTasktype(Integer tasktype) {
		this.tasktype = tasktype;
	}

	public Boolean getIstoptask() {
		return istoptask;
	}

	public void setIstoptask(Boolean istoptask) {
		this.istoptask = istoptask;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProgramname() {
		return programname;
	}

	public void setProgramname(String programname) {
		this.programname = programname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Long getSleepInterval() {
		return sleepInterval;
	}

	public void setSleepInterval(Long sleepInterval) {
		this.sleepInterval = sleepInterval;
	}

	public Boolean getIsproxy() {
		return isproxy;
	}

	public void setIsproxy(Boolean isproxy) {
		this.isproxy = isproxy;
	}

	public String getIslive() {
		return islive;
	}

	public void setIslive(String islive) {
		this.islive = islive;
	}

	public String getProgramparams() {
		return programparams;
	}

	public void setProgramparams(String programparams) {
		this.programparams = programparams;
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

	public void setId(Long id) {
		this.id = id;
	}

	
}