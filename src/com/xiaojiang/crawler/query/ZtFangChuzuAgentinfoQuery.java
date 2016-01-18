package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.ZtFangChuzuAgentinfo;
import com.xiaojiang.util.QueryBase;;

public class ZtFangChuzuAgentinfoQuery extends QueryBase {

	private Long id;

	private String url;

	private String ptyname;

	private String price;

	private String traffic;

	private String agent;

	private String agenttel;

	private Long creationDate;

	private Boolean isCleaned;

	private Long modificationDate;

	public void setId(final Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setUrl(final String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public void setPtyname(final String ptyname){
		this.ptyname = ptyname;
	}

	public String getPtyname(){
		return this.ptyname;
	}

	public void setPrice(final String price){
		this.price = price;
	}

	public String getPrice(){
		return this.price;
	}

	public void setTraffic(final String traffic){
		this.traffic = traffic;
	}

	public String getTraffic(){
		return this.traffic;
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

	public void setIsCleaned(final Boolean isCleaned){
		this.isCleaned = isCleaned;
	}

	public Boolean getIsCleaned(){
		return this.isCleaned;
	}

	public void setModificationDate(final Long modificationDate){
		this.modificationDate = modificationDate;
	}

	public Long getModificationDate(){
		return this.modificationDate;
	}

	@Override
	public String toQuerySql(){
		String sql="select id, url, ptyname, price, traffic, agent, agenttel, creation_date, is_cleaned, modification_date from zt_fang_chuzu_agentinfo where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getPtyname() != null)
			sql += " and ptyname='"+ this.getPtyname()+"'";
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getTraffic() != null)
			sql += " and traffic='"+ this.getTraffic()+"'";
		if(this.getAgent() != null)
			sql += " and agent='"+ this.getAgent()+"'";
		if(this.getAgenttel() != null)
			sql += " and agenttel='"+ this.getAgenttel()+"'";
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getIsCleaned() != null)
			sql += " and is_cleaned="+ this.getIsCleaned();
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
		String sql="select count(*) from zt_fang_chuzu_agentinfo where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getPtyname() != null)
			sql += " and ptyname='"+ this.getPtyname()+"'";
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getTraffic() != null)
			sql += " and traffic='"+ this.getTraffic()+"'";
		if(this.getAgent() != null)
			sql += " and agent='"+ this.getAgent()+"'";
		if(this.getAgenttel() != null)
			sql += " and agenttel='"+ this.getAgenttel()+"'";
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getIsCleaned() != null)
			sql += " and is_cleaned="+ this.getIsCleaned();
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return ZtFangChuzuAgentinfo.class;
	}
	@Override
	public String getDb(){
		return "source";
	}
}