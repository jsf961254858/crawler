package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.ZtDaikanSale;
import com.xiaojiang.util.QueryBase;


public class ZtDaikanSaleQuery extends QueryBase{

	private Long id;

	private Long spiderid;

	private Long scheduleid;

	private String url;

	private String price;

	private String unitprice;

	private String layout;

	private String deco;

	private String high;

	private String metro;

	private String metrostop;

	private String age;

	private String direction;

	private String carsit;

	private String description;

	private String agent;

	private String tel;

	private String ptyname;

	private Long modificationDate;

	private Long creationDate;

	private Boolean isCleaned;

	public void setId(final Long id){
		this.id = id;
	}

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

	public void setUrl(final String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public void setPrice(final String price){
		this.price = price;
	}

	public String getPrice(){
		return this.price;
	}

	public void setUnitprice(final String unitprice){
		this.unitprice = unitprice;
	}

	public String getUnitprice(){
		return this.unitprice;
	}

	public void setLayout(final String layout){
		this.layout = layout;
	}

	public String getLayout(){
		return this.layout;
	}

	public void setDeco(final String deco){
		this.deco = deco;
	}

	public String getDeco(){
		return this.deco;
	}

	public void setHigh(final String high){
		this.high = high;
	}

	public String getHigh(){
		return this.high;
	}

	public void setMetro(final String metro){
		this.metro = metro;
	}

	public String getMetro(){
		return this.metro;
	}

	public void setMetrostop(final String metrostop){
		this.metrostop = metrostop;
	}

	public String getMetrostop(){
		return this.metrostop;
	}

	public void setAge(final String age){
		this.age = age;
	}

	public String getAge(){
		return this.age;
	}

	public void setDirection(final String direction){
		this.direction = direction;
	}

	public String getDirection(){
		return this.direction;
	}

	public void setCarsit(final String carsit){
		this.carsit = carsit;
	}

	public String getCarsit(){
		return this.carsit;
	}

	public void setDescription(final String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setAgent(final String agent){
		this.agent = agent;
	}

	public String getAgent(){
		return this.agent;
	}

	public void setTel(final String tel){
		this.tel = tel;
	}

	public String getTel(){
		return this.tel;
	}

	public void setPtyname(final String ptyname){
		this.ptyname = ptyname;
	}

	public String getPtyname(){
		return this.ptyname;
	}

	public void setModificationDate(final Long modificationDate){
		this.modificationDate = modificationDate;
	}

	public Long getModificationDate(){
		return this.modificationDate;
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

	@Override
	public String toQuerySql(){
		String sql="select id, spiderid, scheduleid, url, price, unitprice, layout, deco, high, metro, metrostop, age, direction, carsit, description, agent, tel, ptyname, modification_date, creation_date, is_cleaned from zt_daikan_sale where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getSpiderid() != null)
			sql += " and spiderid="+ this.getSpiderid();
		if(this.getScheduleid() != null)
			sql += " and scheduleid="+ this.getScheduleid();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getUnitprice() != null)
			sql += " and unitprice='"+ this.getUnitprice()+"'";
		if(this.getLayout() != null)
			sql += " and layout='"+ this.getLayout()+"'";
		if(this.getDeco() != null)
			sql += " and deco='"+ this.getDeco()+"'";
		if(this.getHigh() != null)
			sql += " and high='"+ this.getHigh()+"'";
		if(this.getMetro() != null)
			sql += " and metro='"+ this.getMetro()+"'";
		if(this.getMetrostop() != null)
			sql += " and metrostop='"+ this.getMetrostop()+"'";
		if(this.getAge() != null)
			sql += " and age='"+ this.getAge()+"'";
		if(this.getDirection() != null)
			sql += " and direction='"+ this.getDirection()+"'";
		if(this.getCarsit() != null)
			sql += " and carsit='"+ this.getCarsit()+"'";
		if(this.getDescription() != null)
			sql += " and description='"+ this.getDescription()+"'";
		if(this.getAgent() != null)
			sql += " and agent='"+ this.getAgent()+"'";
		if(this.getTel() != null)
			sql += " and tel='"+ this.getTel()+"'";
		if(this.getPtyname() != null)
			sql += " and ptyname='"+ this.getPtyname()+"'";
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
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
		String sql="select count(*) from zt_daikan_sale where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getSpiderid() != null)
			sql += " and spiderid="+ this.getSpiderid();
		if(this.getScheduleid() != null)
			sql += " and scheduleid="+ this.getScheduleid();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getUnitprice() != null)
			sql += " and unitprice='"+ this.getUnitprice()+"'";
		if(this.getLayout() != null)
			sql += " and layout='"+ this.getLayout()+"'";
		if(this.getDeco() != null)
			sql += " and deco='"+ this.getDeco()+"'";
		if(this.getHigh() != null)
			sql += " and high='"+ this.getHigh()+"'";
		if(this.getMetro() != null)
			sql += " and metro='"+ this.getMetro()+"'";
		if(this.getMetrostop() != null)
			sql += " and metrostop='"+ this.getMetrostop()+"'";
		if(this.getAge() != null)
			sql += " and age='"+ this.getAge()+"'";
		if(this.getDirection() != null)
			sql += " and direction='"+ this.getDirection()+"'";
		if(this.getCarsit() != null)
			sql += " and carsit='"+ this.getCarsit()+"'";
		if(this.getDescription() != null)
			sql += " and description='"+ this.getDescription()+"'";
		if(this.getAgent() != null)
			sql += " and agent='"+ this.getAgent()+"'";
		if(this.getTel() != null)
			sql += " and tel='"+ this.getTel()+"'";
		if(this.getPtyname() != null)
			sql += " and ptyname='"+ this.getPtyname()+"'";
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getIsCleaned() != null)
			sql += " and is_cleaned="+ this.getIsCleaned();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return ZtDaikanSale.class;
	}
	@Override
	public String getDb(){
		return "source";
	}
	
}
