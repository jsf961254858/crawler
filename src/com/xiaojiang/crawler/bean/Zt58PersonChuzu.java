package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;

import com.xiaojiang.bean.BeanBase;


public class Zt58PersonChuzu implements BeanBase{

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

	@DeclareAnnotation("bedroom")
	private Integer bedroom;

	@DeclareAnnotation("livingroom")
	private Integer livingroom;

	@DeclareAnnotation("washroom")
	private Integer washroom;

	@DeclareAnnotation("kitchenroom")
	private Integer kitchenroom;

	@DeclareAnnotation("area")
	private Double area;

	@DeclareAnnotation("total_price")
	private Double totalPrice;

	@DeclareAnnotation("address")
	private String address;

	@DeclareAnnotation("livetime")
	private String livetime;
	
	@DeclareAnnotation("support")
	private String support;

	@DeclareAnnotation("house_type")
	private String houseType;

	@DeclareAnnotation("decoration")
	private String decoration;

	@DeclareAnnotation("floor")
	private String floor;

	@DeclareAnnotation("direction")
	private String direction;

	@DeclareAnnotation("payway")
	private String payway;

	@DeclareAnnotation("update_date")
	private Long updateDate;

	@DeclareAnnotation("creation_date")
	private Long creationDate;

	@DeclareAnnotation("modification_date")
	private Long modificationDate;

	@DeclareAnnotation("url")
	private String url;

	@DeclareAnnotation("is_cleaned")
	private Boolean isCleaned;
	
	@DeclareAnnotation("telephone")
	private String telephone;
	
	@DeclareAnnotation("person")
	private String person;
	
	@DeclareAnnotation("pictureinfo")
	private String pictureinfo;

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

	public void setBedroom(final Integer bedroom){
		this.bedroom = bedroom;
	}

	public Integer getBedroom(){
		return this.bedroom;
	}

	public void setLivingroom(final Integer livingroom){
		this.livingroom = livingroom;
	}

	public Integer getLivingroom(){
		return this.livingroom;
	}

	public void setWashroom(final Integer washroom){
		this.washroom = washroom;
	}

	public Integer getWashroom(){
		return this.washroom;
	}

	public void setKitchenroom(final Integer kitchenroom){
		this.kitchenroom = kitchenroom;
	}

	public Integer getKitchenroom(){
		return this.kitchenroom;
	}

	public void setArea(final Double area){
		this.area = area;
	}

	public Double getArea(){
		return this.area;
	}

	public void setTotalPrice(final Double totalPrice){
		this.totalPrice = totalPrice;
	}

	public Double getTotalPrice(){
		return this.totalPrice;
	}

	public void setAddress(final String address){
		this.address = address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setSupport(final String support){
		this.support = support;
	}

	public String getSupport(){
		return this.support;
	}

	public void setHouseType(final String houseType){
		this.houseType = houseType;
	}

	public String getHouseType(){
		return this.houseType;
	}

	public void setDecoration(final String decoration){
		this.decoration = decoration;
	}

	public String getDecoration(){
		return this.decoration;
	}

	public void setFloor(final String floor){
		this.floor = floor;
	}

	public String getFloor(){
		return this.floor;
	}

	public void setDirection(final String direction){
		this.direction = direction;
	}

	public String getDirection(){
		return this.direction;
	}

	public void setPayway(final String payway){
		this.payway = payway;
	}

	public String getPayway(){
		return this.payway;
	}

	public void setUpdateDate(final Long updateDate){
		this.updateDate = updateDate;
	}

	public Long getUpdateDate(){
		return this.updateDate;
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
		String sql="insert into zt_58_person_chuzu ("+
		"pty_name,"+
		"zone_name,"+
		"dis_name,"+
		"title,"+
		"bedroom,"+
		"livingroom,"+
		"washroom,"+
		"kitchenroom,"+
		"area,"+
		"total_price,"+
		"address,"+
		"livetime,"+
		"support,"+
		"house_type,"+
		"decoration,"+
		"floor,"+
		"direction,"+
		"payway,"+
		"update_date,"+
		"creation_date,"+
		"modification_date,"+
		"url,"+
		"telephone,"+
		"pictureinfo,"+
		"person,"+
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
		if(this.getBedroom() == null){
			sql += this.getBedroom()+",";
		}else{
			sql += this.getBedroom()+",";
		}
		if(this.getLivingroom() == null){
			sql += this.getLivingroom()+",";
		}else{
			sql += this.getLivingroom()+",";
		}
		if(this.getWashroom() == null){
			sql += this.getWashroom()+",";
		}else{
			sql += this.getWashroom()+",";
		}
		if(this.getKitchenroom() == null){
			sql += this.getKitchenroom()+",";
		}else{
			sql += this.getKitchenroom()+",";
		}
		if(this.getArea() == null){
			sql += this.getArea()+",";
		}else{
			sql += this.getArea()+",";
		}
		if(this.getTotalPrice() == null){
			sql += this.getTotalPrice()+",";
		}else{
			sql += this.getTotalPrice()+",";
		}
		if(this.getAddress() == null){
			sql += "'"+this.getAddress()+"'"+",";
		}else{
			sql += "'"+this.getAddress().replaceAll("'", "")+"'"+",";
		}
		if(this.getLivetime() == null){
			sql += "'"+this.getLivetime()+"'"+",";
		}else{
			sql += "'"+this.getLivetime().replaceAll("'", "")+"'"+",";
		}
		if(this.getSupport() == null){
			sql += "'"+this.getSupport()+"'"+",";
		}else{
			sql += "'"+this.getSupport().replaceAll("'", "")+"'"+",";
		}
		if(this.getHouseType() == null){
			sql += "'"+this.getHouseType()+"'"+",";
		}else{
			sql += "'"+this.getHouseType().replaceAll("'", "")+"'"+",";
		}
		if(this.getDecoration() == null){
			sql += "'"+this.getDecoration()+"'"+",";
		}else{
			sql += "'"+this.getDecoration().replaceAll("'", "")+"'"+",";
		}
		if(this.getFloor() == null){
			sql += "'"+this.getFloor()+"'"+",";
		}else{
			sql += "'"+this.getFloor().replaceAll("'", "")+"'"+",";
		}
		if(this.getDirection() == null){
			sql += "'"+this.getDirection()+"'"+",";
		}else{
			sql += "'"+this.getDirection().replaceAll("'", "")+"'"+",";
		}
		if(this.getPayway() == null){
			sql += "'"+this.getPayway()+"'"+",";
		}else{
			sql += "'"+this.getPayway().replaceAll("'", "")+"'"+",";
		}
		if(this.getUpdateDate() == null){
			sql += this.getUpdateDate()+",";
		}else{
			sql += this.getUpdateDate()+",";
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
		if(this.getTelephone() == null){
			sql += "'"+this.getTelephone()+"'"+",";
		}else{
			sql += "'"+this.getTelephone().replaceAll("'", "")+"'"+",";
		}
		if(this.getPictureinfo() == null){
			sql += "'"+this.getPictureinfo()+"'"+",";
		}else{
			sql += "'"+this.getPictureinfo().replaceAll("'", "")+"'"+",";
		}
		if(this.getPerson() == null){
			sql += "'"+this.getPerson()+"'"+",";
		}else{
			sql += "'"+this.getPerson().replaceAll("'", "")+"'"+",";
		}
		if(this.getIsCleaned() == null){
			sql += this.getIsCleaned();
		}else{
			sql += this.getIsCleaned();
		}
		sql += ")";
		//System.out.println(sql);
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update zt_58_person_chuzu set ";
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
		if(this.getBedroom() != null) {
			sql += "bedroom="+ this.getBedroom()+",";
		}
		if(this.getLivingroom() != null) {
			sql += "livingroom="+ this.getLivingroom()+",";
		}
		if(this.getWashroom() != null) {
			sql += "washroom="+ this.getWashroom()+",";
		}
		if(this.getKitchenroom() != null) {
			sql += "kitchenroom="+ this.getKitchenroom()+",";
		}
		if(this.getArea() != null) {
			sql += "area="+ this.getArea()+",";
		}
		if(this.getTotalPrice() != null) {
			sql += "total_price="+ this.getTotalPrice()+",";
		}
		if(this.getAddress() != null) {
			sql += "address='"+ this.getAddress().replaceAll("'", "")+"'"+",";
		}
		if(this.getLivetime() != null) {
			sql += "livetime='"+ this.getLivetime().replaceAll("'", "")+"'"+",";
		}
		if(this.getSupport() != null) {
			sql += "support='"+ this.getSupport().replaceAll("'", "")+"'"+",";
		}
		if(this.getHouseType() != null) {
			sql += "house_type='"+ this.getHouseType().replaceAll("'", "")+"'"+",";
		}
		if(this.getDecoration() != null) {
			sql += "decoration='"+ this.getDecoration().replaceAll("'", "")+"'"+",";
		}
		if(this.getFloor() != null) {
			sql += "floor='"+ this.getFloor().replaceAll("'", "")+"'"+",";
		}
		if(this.getDirection() != null) {
			sql += "direction='"+ this.getDirection().replaceAll("'", "")+"'"+",";
		}
		if(this.getPayway() != null) {
			sql += "payway='"+ this.getPayway().replaceAll("'", "")+"'"+",";
		}
		if(this.getUpdateDate() != null) {
			sql += "update_date="+ this.getUpdateDate()+",";
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
		if(this.getTelephone() != null) {
			sql += "telephone='"+ this.getTelephone().replaceAll("'", "")+"'"+",";
		}
		if(this.getPerson() != null) {
			sql += "person='"+ this.getPerson().replaceAll("'", "")+"'"+",";
		}
		if(this.getPictureinfo() != null) {
			sql += "pictureinfo='"+ this.getPictureinfo().replaceAll("'", "")+"'"+",";
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPictureinfo() {
		return pictureinfo;
	}

	public void setPictureinfo(String pictureinfo) {
		this.pictureinfo = pictureinfo;
	}

	public String getLivetime() {
		return livetime;
	}

	public void setLivetime(String livetime) {
		this.livetime = livetime;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
}
