package com.xiaojiang.bean;

import com.xiaojiang.util.QueryBase;


public class DataTaskQuery extends QueryBase {

	private Long id;

	private Long userid;

	private Long groupid;

	private String subtaskurl;

	private Integer tasktype;

	private Boolean istoptask;

	private String code;

	private String city;

	private String type;

	private String programname;

	private String name;

	private String datatype;

	private String website;

	private String webpage;

	private Integer start;

	private Integer end;

	private Integer period;

	private Long sleepInterval;

	private Boolean isproxy;

	private String islive;

	private String programparams;

	private Long creationDate;

	private Long modificationDate;

	public void setId(final Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setUserid(final Long userid){
		this.userid = userid;
	}

	public Long getUserid(){
		return this.userid;
	}

	public void setGroupid(final Long groupid){
		this.groupid = groupid;
	}

	public Long getGroupid(){
		return this.groupid;
	}

	public void setSubtaskurl(final String subtaskurl){
		this.subtaskurl = subtaskurl;
	}

	public String getSubtaskurl(){
		return this.subtaskurl;
	}

	public void setTasktype(final Integer tasktype){
		this.tasktype = tasktype;
	}

	public Integer getTasktype(){
		return this.tasktype;
	}

	public void setIstoptask(final Boolean istoptask){
		this.istoptask = istoptask;
	}

	public Boolean getIstoptask(){
		return this.istoptask;
	}

	public void setCode(final String code){
		this.code = code;
	}

	public String getCode(){
		return this.code;
	}

	public void setCity(final String city){
		this.city = city;
	}

	public String getCity(){
		return this.city;
	}

	public void setType(final String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setProgramname(final String programname){
		this.programname = programname;
	}

	public String getProgramname(){
		return this.programname;
	}

	public void setName(final String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setDatatype(final String datatype){
		this.datatype = datatype;
	}

	public String getDatatype(){
		return this.datatype;
	}

	public void setWebsite(final String website){
		this.website = website;
	}

	public String getWebsite(){
		return this.website;
	}

	public void setWebpage(final String webpage){
		this.webpage = webpage;
	}

	public String getWebpage(){
		return this.webpage;
	}

	public void setStart(final Integer start){
		this.start = start;
	}

	public Integer getStart(){
		return this.start;
	}

	public void setEnd(final Integer end){
		this.end = end;
	}

	public Integer getEnd(){
		return this.end;
	}

	public void setPeriod(final Integer period){
		this.period = period;
	}

	public Integer getPeriod(){
		return this.period;
	}

	public void setSleepInterval(final Long sleepInterval){
		this.sleepInterval = sleepInterval;
	}

	public Long getSleepInterval(){
		return this.sleepInterval;
	}

	public void setIsproxy(final Boolean isproxy){
		this.isproxy = isproxy;
	}

	public Boolean getIsproxy(){
		return this.isproxy;
	}

	public void setIslive(final String islive){
		this.islive = islive;
	}

	public String getIslive(){
		return this.islive;
	}

	public void setProgramparams(final String programparams){
		this.programparams = programparams;
	}

	public String getProgramparams(){
		return this.programparams;
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
		String sql="select id, userid, groupid, subtaskurl, tasktype, istoptask, code, city, type, programname, name, datatype, website, webpage, start, end, period, sleep_interval, isproxy, islive, programparams, creation_date, modification_date from data_task where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getUserid() != null)
			sql += " and userid="+ this.getUserid();
		if(this.getGroupid() != null)
			sql += " and groupid="+ this.getGroupid();
		if(this.getSubtaskurl() != null)
			sql += " and subtaskurl='"+ this.getSubtaskurl()+"'";
		if(this.getTasktype() != null)
			sql += " and tasktype="+ this.getTasktype();
		if(this.getIstoptask() != null)
			sql += " and istoptask="+ this.getIstoptask();
		if(this.getCode() != null)
			sql += " and code='"+ this.getCode()+"'";
		if(this.getCity() != null)
			sql += " and city='"+ this.getCity()+"'";
		if(this.getType() != null)
			sql += " and type='"+ this.getType()+"'";
		if(this.getProgramname() != null)
			sql += " and programname='"+ this.getProgramname()+"'";
		if(this.getName() != null)
			sql += " and name='"+ this.getName()+"'";
		if(this.getDatatype() != null)
			sql += " and datatype='"+ this.getDatatype()+"'";
		if(this.getWebsite() != null)
			sql += " and website='"+ this.getWebsite()+"'";
		if(this.getWebpage() != null)
			sql += " and webpage='"+ this.getWebpage()+"'";
		if(this.getStart() != null)
			sql += " and start="+ this.getStart();
		if(this.getEnd() != null)
			sql += " and end="+ this.getEnd();
		if(this.getPeriod() != null)
			sql += " and period="+ this.getPeriod();
		if(this.getSleepInterval() != null)
			sql += " and sleep_interval="+ this.getSleepInterval();
		if(this.getIsproxy() != null)
			sql += " and isproxy="+ this.getIsproxy();
		if(this.getIslive() != null)
			sql += " and islive='"+ this.getIslive()+"'";
		if(this.getProgramparams() != null)
			sql += " and programparams='"+ this.getProgramparams()+"'";
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
		String sql="select count(*) from data_task where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getUserid() != null)
			sql += " and userid="+ this.getUserid();
		if(this.getGroupid() != null)
			sql += " and groupid="+ this.getGroupid();
		if(this.getSubtaskurl() != null)
			sql += " and subtaskurl='"+ this.getSubtaskurl()+"'";
		if(this.getTasktype() != null)
			sql += " and tasktype="+ this.getTasktype();
		if(this.getIstoptask() != null)
			sql += " and istoptask="+ this.getIstoptask();
		if(this.getCode() != null)
			sql += " and code='"+ this.getCode()+"'";
		if(this.getCity() != null)
			sql += " and city='"+ this.getCity()+"'";
		if(this.getType() != null)
			sql += " and type='"+ this.getType()+"'";
		if(this.getProgramname() != null)
			sql += " and programname='"+ this.getProgramname()+"'";
		if(this.getName() != null)
			sql += " and name='"+ this.getName()+"'";
		if(this.getDatatype() != null)
			sql += " and datatype='"+ this.getDatatype()+"'";
		if(this.getWebsite() != null)
			sql += " and website='"+ this.getWebsite()+"'";
		if(this.getWebpage() != null)
			sql += " and webpage='"+ this.getWebpage()+"'";
		if(this.getStart() != null)
			sql += " and start="+ this.getStart();
		if(this.getEnd() != null)
			sql += " and end="+ this.getEnd();
		if(this.getPeriod() != null)
			sql += " and period="+ this.getPeriod();
		if(this.getSleepInterval() != null)
			sql += " and sleep_interval="+ this.getSleepInterval();
		if(this.getIsproxy() != null)
			sql += " and isproxy="+ this.getIsproxy();
		if(this.getIslive() != null)
			sql += " and islive='"+ this.getIslive()+"'";
		if(this.getProgramparams() != null)
			sql += " and programparams='"+ this.getProgramparams()+"'";
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
		return DataTask.class;
	}
	@Override
	public String getDb(){
		return "global";
	}
}