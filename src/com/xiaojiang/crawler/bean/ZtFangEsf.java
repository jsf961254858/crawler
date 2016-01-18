package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;

import com.xiaojiang.bean.BeanBase;

public class ZtFangEsf implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("url")
	private String url;

	@DeclareAnnotation("ptyname")
	private String ptyname;

	@DeclareAnnotation("price")
	private String price;

	@DeclareAnnotation("traffic")
	private String traffic;

	@DeclareAnnotation("agent")
	private String agent;

	@DeclareAnnotation("agenttel")
	private String agenttel;

	@DeclareAnnotation("creation_date")
	private Long creationDate;

	@DeclareAnnotation("is_cleaned")
	private Boolean isCleaned;

	@DeclareAnnotation("modification_date")
	private Long modificationDate;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
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
	public String toInsertSql(){
		String sql="insert into zt_fang_esf ("+
		"url,"+
		"ptyname,"+
		"price,"+
		"traffic,"+
		"agent,"+
		"agenttel,"+
		"creation_date,"+
		"is_cleaned,"+
		"modification_date"+
		") "+
		"values (";
		if(this.getUrl() == null){
			sql += "'"+this.getUrl()+"'"+",";
		}else{
			sql += "'"+this.getUrl().replaceAll("'", "")+"'"+",";
		}
		if(this.getPtyname() == null){
			sql += "'"+this.getPtyname()+"'"+",";
		}else{
			sql += "'"+this.getPtyname().replaceAll("'", "")+"'"+",";
		}
		if(this.getPrice() == null){
			sql += "'"+this.getPrice()+"'"+",";
		}else{
			sql += "'"+this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getTraffic() == null){
			sql += "'"+this.getTraffic()+"'"+",";
		}else{
			sql += "'"+this.getTraffic().replaceAll("'", "")+"'"+",";
		}
		if(this.getAgent() == null){
			sql += "'"+this.getAgent()+"'"+",";
		}else{
			sql += "'"+this.getAgent().replaceAll("'", "")+"'"+",";
		}
		if(this.getAgenttel() == null){
			sql += "'"+this.getAgenttel()+"'"+",";
		}else{
			sql += "'"+this.getAgenttel().replaceAll("'", "")+"'"+",";
		}
		if(this.getCreationDate() == null){
			sql += this.getCreationDate()+",";
		}else{
			sql += this.getCreationDate()+",";
		}
		if(this.getIsCleaned() == null){
			sql += this.getIsCleaned()+",";
		}else{
			sql += this.getIsCleaned()+",";
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
		String sql="update zt_fang_esf set ";
		if(this.getUrl() != null) {
			sql += "url='"+ this.getUrl().replaceAll("'", "")+"'"+",";
		}
		if(this.getPtyname() != null) {
			sql += "ptyname='"+ this.getPtyname().replaceAll("'", "")+"'"+",";
		}
		if(this.getPrice() != null) {
			sql += "price='"+ this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getTraffic() != null) {
			sql += "traffic='"+ this.getTraffic().replaceAll("'", "")+"'"+",";
		}
		if(this.getAgent() != null) {
			sql += "agent='"+ this.getAgent().replaceAll("'", "")+"'"+",";
		}
		if(this.getAgenttel() != null) {
			sql += "agenttel='"+ this.getAgenttel().replaceAll("'", "")+"'"+",";
		}
		if(this.getCreationDate() != null) {
			sql += "creation_date="+ this.getCreationDate()+",";
		}
		if(this.getIsCleaned() != null) {
			sql += "is_cleaned="+ this.getIsCleaned()+",";
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
		return "source";
	}
}