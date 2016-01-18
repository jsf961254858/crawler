package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;

import com.xiaojiang.bean.BeanBase;

public class Zt58ChuzuAgentinfo implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("pty_name")
	private String ptyName;

	@DeclareAnnotation("zone_name")
	private String zoneName;

	@DeclareAnnotation("dis_name")
	private String disName;

	@DeclareAnnotation("title")
	private String title;

	@DeclareAnnotation("price")
	private String price;

	@DeclareAnnotation("detail")
	private String detail;

	@DeclareAnnotation("floor")
	private String floor;

	@DeclareAnnotation("address")
	private String address;

	@DeclareAnnotation("agent")
	private String agent;

	@DeclareAnnotation("agenttel")
	private String agenttel;

	@DeclareAnnotation("creation_date")
	private Long creationDate;

	@DeclareAnnotation("modification_date")
	private Long modificationDate;

	@DeclareAnnotation("url")
	private String url;

	@DeclareAnnotation("is_cleaned")
	private Boolean isCleaned;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
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
	public String toInsertSql(){
		String sql="insert into zt_58_chuzu_agentinfo ("+
		"pty_name,"+
		"zone_name,"+
		"dis_name,"+
		"title,"+
		"price,"+
		"detail,"+
		"floor,"+
		"address,"+
		"agent,"+
		"agenttel,"+
		"creation_date,"+
		"modification_date,"+
		"url,"+
		"is_cleaned"+
		") "+
		"values (";
		if(this.getPtyName() == null){
			sql += "'"+this.getPtyName()+"'"+",";
		}else{
			sql += "'"+this.getPtyName().replaceAll("'", "")+"'"+",";
		}
		if(this.getZoneName() == null){
			sql += "'"+this.getZoneName()+"'"+",";
		}else{
			sql += "'"+this.getZoneName().replaceAll("'", "")+"'"+",";
		}
		if(this.getDisName() == null){
			sql += "'"+this.getDisName()+"'"+",";
		}else{
			sql += "'"+this.getDisName().replaceAll("'", "")+"'"+",";
		}
		if(this.getTitle() == null){
			sql += "'"+this.getTitle()+"'"+",";
		}else{
			sql += "'"+this.getTitle().replaceAll("'", "")+"'"+",";
		}
		if(this.getPrice() == null){
			sql += "'"+this.getPrice()+"'"+",";
		}else{
			sql += "'"+this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getDetail() == null){
			sql += "'"+this.getDetail()+"'"+",";
		}else{
			sql += "'"+this.getDetail().replaceAll("'", "")+"'"+",";
		}
		if(this.getFloor() == null){
			sql += "'"+this.getFloor()+"'"+",";
		}else{
			sql += "'"+this.getFloor().replaceAll("'", "")+"'"+",";
		}
		if(this.getAddress() == null){
			sql += "'"+this.getAddress()+"'"+",";
		}else{
			sql += "'"+this.getAddress().replaceAll("'", "")+"'"+",";
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
		if(this.getModificationDate() == null){
			sql += this.getModificationDate()+",";
		}else{
			sql += this.getModificationDate()+",";
		}
		if(this.getUrl() == null){
			sql += "'"+this.getUrl()+"'"+",";
		}else{
			sql += "'"+this.getUrl().replaceAll("'", "")+"'"+",";
		}
		if(this.getIsCleaned() == null){
			sql += this.getIsCleaned();
		}else{
			sql += this.getIsCleaned();
		}
		sql += ")";
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update zt_58_chuzu_agentinfo set ";
		if(this.getPtyName() != null) {
			sql += "pty_name='"+ this.getPtyName().replaceAll("'", "")+"'"+",";
		}
		if(this.getZoneName() != null) {
			sql += "zone_name='"+ this.getZoneName().replaceAll("'", "")+"'"+",";
		}
		if(this.getDisName() != null) {
			sql += "dis_name='"+ this.getDisName().replaceAll("'", "")+"'"+",";
		}
		if(this.getTitle() != null) {
			sql += "title='"+ this.getTitle().replaceAll("'", "")+"'"+",";
		}
		if(this.getPrice() != null) {
			sql += "price='"+ this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getDetail() != null) {
			sql += "detail='"+ this.getDetail().replaceAll("'", "")+"'"+",";
		}
		if(this.getFloor() != null) {
			sql += "floor='"+ this.getFloor().replaceAll("'", "")+"'"+",";
		}
		if(this.getAddress() != null) {
			sql += "address='"+ this.getAddress().replaceAll("'", "")+"'"+",";
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
		if(this.getModificationDate() != null) {
			sql += "modification_date="+ this.getModificationDate()+",";
		}
		if(this.getUrl() != null) {
			sql += "url='"+ this.getUrl().replaceAll("'", "")+"'"+",";
		}
		if(this.getIsCleaned() != null) {
			sql += "is_cleaned="+ this.getIsCleaned();
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