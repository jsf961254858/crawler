package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.ZtEejuSale;
import com.xiaojiang.util.QueryBase;


public class ZtEejuSaleQuery extends QueryBase {

	private Long id;

	private String url;

	private String ptyname;

	private String perprice;

	private String price;

	private String inch;

	private String high;

	private String deco;

	private String direction;

	private String roomtype;

	private String age;

	private String addr;

	private String srcid;

	private String info;

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

	public void setPerprice(final String perprice){
		this.perprice = perprice;
	}

	public String getPerprice(){
		return this.perprice;
	}

	public void setPrice(final String price){
		this.price = price;
	}

	public String getPrice(){
		return this.price;
	}

	public void setInch(final String inch){
		this.inch = inch;
	}

	public String getInch(){
		return this.inch;
	}

	public void setHigh(final String high){
		this.high = high;
	}

	public String getHigh(){
		return this.high;
	}

	public void setDeco(final String deco){
		this.deco = deco;
	}

	public String getDeco(){
		return this.deco;
	}

	public void setDirection(final String direction){
		this.direction = direction;
	}

	public String getDirection(){
		return this.direction;
	}

	public void setRoomtype(final String roomtype){
		this.roomtype = roomtype;
	}

	public String getRoomtype(){
		return this.roomtype;
	}

	public void setAge(final String age){
		this.age = age;
	}

	public String getAge(){
		return this.age;
	}

	public void setAddr(final String addr){
		this.addr = addr;
	}

	public String getAddr(){
		return this.addr;
	}

	public void setSrcid(final String srcid){
		this.srcid = srcid;
	}

	public String getSrcid(){
		return this.srcid;
	}

	public void setInfo(final String info){
		this.info = info;
	}

	public String getInfo(){
		return this.info;
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
		String sql="select id, url, ptyname, perprice, price, inch, high, deco, direction, roomtype, age, addr, srcid, info, creation_date, is_cleaned, modification_date from zt_eeju_sale where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getPtyname() != null)
			sql += " and ptyname='"+ this.getPtyname()+"'";
		if(this.getPerprice() != null)
			sql += " and perprice='"+ this.getPerprice()+"'";
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getInch() != null)
			sql += " and inch='"+ this.getInch()+"'";
		if(this.getHigh() != null)
			sql += " and high='"+ this.getHigh()+"'";
		if(this.getDeco() != null)
			sql += " and deco='"+ this.getDeco()+"'";
		if(this.getDirection() != null)
			sql += " and direction='"+ this.getDirection()+"'";
		if(this.getRoomtype() != null)
			sql += " and roomtype='"+ this.getRoomtype()+"'";
		if(this.getAge() != null)
			sql += " and age='"+ this.getAge()+"'";
		if(this.getAddr() != null)
			sql += " and addr='"+ this.getAddr()+"'";
		if(this.getSrcid() != null)
			sql += " and srcid='"+ this.getSrcid()+"'";
		if(this.getInfo() != null)
			sql += " and info='"+ this.getInfo()+"'";
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
		String sql="select count(*) from zt_eeju_sale where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getPtyname() != null)
			sql += " and ptyname='"+ this.getPtyname()+"'";
		if(this.getPerprice() != null)
			sql += " and perprice='"+ this.getPerprice()+"'";
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getInch() != null)
			sql += " and inch='"+ this.getInch()+"'";
		if(this.getHigh() != null)
			sql += " and high='"+ this.getHigh()+"'";
		if(this.getDeco() != null)
			sql += " and deco='"+ this.getDeco()+"'";
		if(this.getDirection() != null)
			sql += " and direction='"+ this.getDirection()+"'";
		if(this.getRoomtype() != null)
			sql += " and roomtype='"+ this.getRoomtype()+"'";
		if(this.getAge() != null)
			sql += " and age='"+ this.getAge()+"'";
		if(this.getAddr() != null)
			sql += " and addr='"+ this.getAddr()+"'";
		if(this.getSrcid() != null)
			sql += " and srcid='"+ this.getSrcid()+"'";
		if(this.getInfo() != null)
			sql += " and info='"+ this.getInfo()+"'";
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
		return ZtEejuSale.class;
	}
	@Override
	public String getDb(){
		return "source";
	}
}