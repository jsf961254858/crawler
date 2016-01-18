package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.Zt58ChuzuAgentinfo;
import com.xiaojiang.util.QueryBase;

public class Zt58ChuzuAgentinfoQuery extends QueryBase {

	private Long id;

	private String ptyName;

	private String zoneName;

	private String disName;

	private String title;

	private String price;

	private String detail;

	private String floor;

	private String address;

	private String agent;

	private String agenttel;

	private Long creationDate;

	private Long modificationDate;

	private String url;

	private Boolean isCleaned;

	public void setId(final Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setPtyName(final String ptyName){
		this.ptyName = ptyName;
	}

	public String getPtyName(){
		return this.ptyName;
	}

	public void setZoneName(final String zoneName){
		this.zoneName = zoneName;
	}

	public String getZoneName(){
		return this.zoneName;
	}

	public void setDisName(final String disName){
		this.disName = disName;
	}

	public String getDisName(){
		return this.disName;
	}

	public void setTitle(final String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setPrice(final String price){
		this.price = price;
	}

	public String getPrice(){
		return this.price;
	}

	public void setDetail(final String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return this.detail;
	}

	public void setFloor(final String floor){
		this.floor = floor;
	}

	public String getFloor(){
		return this.floor;
	}

	public void setAddress(final String address){
		this.address = address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setAgent(final String agent){
		this.agent = agent;
	}

	public String getAgent(){
		return this.agent;
	}

	public void setAgenttel(final String agenttel){
		this.agenttel = agenttel;
	}

	public String getAgenttel(){
		return this.agenttel;
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

	public void setUrl(final String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public void setIsCleaned(final Boolean isCleaned){
		this.isCleaned = isCleaned;
	}

	public Boolean getIsCleaned(){
		return this.isCleaned;
	}

	@Override
	public String toQuerySql(){
		String sql="select id, pty_name, zone_name, dis_name, title, price, detail, floor, address, agent, agenttel, creation_date, modification_date, url, is_cleaned from zt_58_chuzu_agentinfo where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getPtyName() != null)
			sql += " and pty_name='"+ this.getPtyName()+"'";
		if(this.getZoneName() != null)
			sql += " and zone_name='"+ this.getZoneName()+"'";
		if(this.getDisName() != null)
			sql += " and dis_name='"+ this.getDisName()+"'";
		if(this.getTitle() != null)
			sql += " and title='"+ this.getTitle()+"'";
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getDetail() != null)
			sql += " and detail='"+ this.getDetail()+"'";
		if(this.getFloor() != null)
			sql += " and floor='"+ this.getFloor()+"'";
		if(this.getAddress() != null)
			sql += " and address='"+ this.getAddress()+"'";
		if(this.getAgent() != null)
			sql += " and agent='"+ this.getAgent()+"'";
		if(this.getAgenttel() != null)
			sql += " and agenttel='"+ this.getAgenttel()+"'";
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getIsCleaned() != null)
			sql += " and is_cleaned="+ this.getIsCleaned();
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
		String sql="select count(*) from zt_58_chuzu_agentinfo where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getPtyName() != null)
			sql += " and pty_name='"+ this.getPtyName()+"'";
		if(this.getZoneName() != null)
			sql += " and zone_name='"+ this.getZoneName()+"'";
		if(this.getDisName() != null)
			sql += " and dis_name='"+ this.getDisName()+"'";
		if(this.getTitle() != null)
			sql += " and title='"+ this.getTitle()+"'";
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getDetail() != null)
			sql += " and detail='"+ this.getDetail()+"'";
		if(this.getFloor() != null)
			sql += " and floor='"+ this.getFloor()+"'";
		if(this.getAddress() != null)
			sql += " and address='"+ this.getAddress()+"'";
		if(this.getAgent() != null)
			sql += " and agent='"+ this.getAgent()+"'";
		if(this.getAgenttel() != null)
			sql += " and agenttel='"+ this.getAgenttel()+"'";
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getIsCleaned() != null)
			sql += " and is_cleaned="+ this.getIsCleaned();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return Zt58ChuzuAgentinfo.class;
	}
	@Override
	public String getDb(){
		return "source";
	}
}