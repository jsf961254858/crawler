package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;

import com.xiaojiang.bean.BeanBase;

public class ZtEejuSale implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("url")
	private String url;

	@DeclareAnnotation("ptyname")
	private String ptyname;

	@DeclareAnnotation("perprice")
	private String perprice;

	@DeclareAnnotation("price")
	private String price;

	@DeclareAnnotation("inch")
	private String inch;

	@DeclareAnnotation("high")
	private String high;

	@DeclareAnnotation("deco")
	private String deco;

	@DeclareAnnotation("direction")
	private String direction;

	@DeclareAnnotation("roomtype")
	private String roomtype;

	@DeclareAnnotation("age")
	private String age;

	@DeclareAnnotation("addr")
	private String addr;

	@DeclareAnnotation("srcid")
	private String srcid;

	@DeclareAnnotation("info")
	private String info;

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
	public String toInsertSql(){
		String sql="insert into zt_eeju_sale ("+
		"url,"+
		"ptyname,"+
		"perprice,"+
		"price,"+
		"inch,"+
		"high,"+
		"deco,"+
		"direction,"+
		"roomtype,"+
		"age,"+
		"addr,"+
		"srcid,"+
		"info,"+
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
		if(this.getPerprice() == null){
			sql += "'"+this.getPerprice()+"'"+",";
		}else{
			sql += "'"+this.getPerprice().replaceAll("'", "")+"'"+",";
		}
		if(this.getPrice() == null){
			sql += "'"+this.getPrice()+"'"+",";
		}else{
			sql += "'"+this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getInch() == null){
			sql += "'"+this.getInch()+"'"+",";
		}else{
			sql += "'"+this.getInch().replaceAll("'", "")+"'"+",";
		}
		if(this.getHigh() == null){
			sql += "'"+this.getHigh()+"'"+",";
		}else{
			sql += "'"+this.getHigh().replaceAll("'", "")+"'"+",";
		}
		if(this.getDeco() == null){
			sql += "'"+this.getDeco()+"'"+",";
		}else{
			sql += "'"+this.getDeco().replaceAll("'", "")+"'"+",";
		}
		if(this.getDirection() == null){
			sql += "'"+this.getDirection()+"'"+",";
		}else{
			sql += "'"+this.getDirection().replaceAll("'", "")+"'"+",";
		}
		if(this.getRoomtype() == null){
			sql += "'"+this.getRoomtype()+"'"+",";
		}else{
			sql += "'"+this.getRoomtype().replaceAll("'", "")+"'"+",";
		}
		if(this.getAge() == null){
			sql += "'"+this.getAge()+"'"+",";
		}else{
			sql += "'"+this.getAge().replaceAll("'", "")+"'"+",";
		}
		if(this.getAddr() == null){
			sql += "'"+this.getAddr()+"'"+",";
		}else{
			sql += "'"+this.getAddr().replaceAll("'", "")+"'"+",";
		}
		if(this.getSrcid() == null){
			sql += "'"+this.getSrcid()+"'"+",";
		}else{
			sql += "'"+this.getSrcid().replaceAll("'", "")+"'"+",";
		}
		if(this.getInfo() == null){
			sql += "'"+this.getInfo()+"'"+",";
		}else{
			sql += "'"+this.getInfo().replaceAll("'", "")+"'"+",";
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
		String sql="update zt_eeju_sale set ";
		if(this.getUrl() != null) {
			sql += "url='"+ this.getUrl().replaceAll("'", "")+"'"+",";
		}
		if(this.getPtyname() != null) {
			sql += "ptyname='"+ this.getPtyname().replaceAll("'", "")+"'"+",";
		}
		if(this.getPerprice() != null) {
			sql += "perprice='"+ this.getPerprice().replaceAll("'", "")+"'"+",";
		}
		if(this.getPrice() != null) {
			sql += "price='"+ this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getInch() != null) {
			sql += "inch='"+ this.getInch().replaceAll("'", "")+"'"+",";
		}
		if(this.getHigh() != null) {
			sql += "high='"+ this.getHigh().replaceAll("'", "")+"'"+",";
		}
		if(this.getDeco() != null) {
			sql += "deco='"+ this.getDeco().replaceAll("'", "")+"'"+",";
		}
		if(this.getDirection() != null) {
			sql += "direction='"+ this.getDirection().replaceAll("'", "")+"'"+",";
		}
		if(this.getRoomtype() != null) {
			sql += "roomtype='"+ this.getRoomtype().replaceAll("'", "")+"'"+",";
		}
		if(this.getAge() != null) {
			sql += "age='"+ this.getAge().replaceAll("'", "")+"'"+",";
		}
		if(this.getAddr() != null) {
			sql += "addr='"+ this.getAddr().replaceAll("'", "")+"'"+",";
		}
		if(this.getSrcid() != null) {
			sql += "srcid='"+ this.getSrcid().replaceAll("'", "")+"'"+",";
		}
		if(this.getInfo() != null) {
			sql += "info='"+ this.getInfo().replaceAll("'", "")+"'"+",";
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