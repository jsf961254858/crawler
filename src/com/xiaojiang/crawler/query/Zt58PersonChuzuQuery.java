package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.Zt58PersonChuzu;
import com.xiaojiang.util.QueryBase;

public class Zt58PersonChuzuQuery extends QueryBase {

	private Long id;

	private String ptyName;

	private String zoneName;

	private String disName;

	private String title;

	private Integer bedroom;

	private Integer livingroom;

	private Integer washroom;

	private Integer kitchenroom;

	private Double area;

	private Double totalPrice;

	private String address;
	
	private String livetime;

	private String support;

	private String houseType;

	private String decoration;

	private String floor;

	private String direction;

	private String payway;

	private Long updateDate;

	private Long creationDate;

	private Long modificationDate;

	private String url;

	private Boolean isCleaned;
	
	private String telephone;
	
	private String person;
	
	private String pictureinfo;
	

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
	public String toQuerySql(){
		String sql="select id, pty_name, zone_name, dis_name, title, bedroom, livingroom, washroom, kitchenroom, area, total_price, address,livetime, support, house_type, decoration, floor, direction, payway, update_date, creation_date, modification_date, url, telephone,person,pictureinfo, is_cleaned from zt_58_person_chuzu where 1=1";

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
		if(this.getBedroom() != null)
			sql += " and bedroom="+ this.getBedroom();
		if(this.getLivingroom() != null)
			sql += " and livingroom="+ this.getLivingroom();
		if(this.getWashroom() != null)
			sql += " and washroom="+ this.getWashroom();
		if(this.getKitchenroom() != null)
			sql += " and kitchenroom="+ this.getKitchenroom();
		if(this.getArea() != null)
			sql += " and area="+ this.getArea();
		if(this.getTotalPrice() != null)
			sql += " and total_price="+ this.getTotalPrice();
		if(this.getAddress() != null)
			sql += " and address='"+ this.getAddress()+"'";
		if(this.getLivetime() != null)
			sql += " and livetime='"+ this.getLivetime()+"'";
		if(this.getSupport() != null)
			sql += " and support='"+ this.getSupport()+"'";
		if(this.getHouseType() != null)
			sql += " and house_type='"+ this.getHouseType()+"'";
		if(this.getDecoration() != null)
			sql += " and decoration='"+ this.getDecoration()+"'";
		if(this.getFloor() != null)
			sql += " and floor='"+ this.getFloor()+"'";
		if(this.getDirection() != null)
			sql += " and direction='"+ this.getDirection()+"'";
		if(this.getPayway() != null)
			sql += " and payway='"+ this.getPayway()+"'";
		if(this.getUpdateDate() != null)
			sql += " and update_date="+ this.getUpdateDate();
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getTelephone() != null)
			sql += " and telephone="+ this.getTelephone();
		if(this.getPerson() != null)
			sql += " and person="+ this.getPerson();
		if(this.getPictureinfo() != null)
			sql += " and pictureinfo="+ this.getPictureinfo();
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
		String sql="select count(*) from zt_58_person_chuzu where 1=1";

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
		if(this.getBedroom() != null)
			sql += " and bedroom="+ this.getBedroom();
		if(this.getLivingroom() != null)
			sql += " and livingroom="+ this.getLivingroom();
		if(this.getWashroom() != null)
			sql += " and washroom="+ this.getWashroom();
		if(this.getKitchenroom() != null)
			sql += " and kitchenroom="+ this.getKitchenroom();
		if(this.getArea() != null)
			sql += " and area="+ this.getArea();
		if(this.getTotalPrice() != null)
			sql += " and total_price="+ this.getTotalPrice();
		if(this.getAddress() != null)
			sql += " and address='"+ this.getAddress()+"'";
		if(this.getLivetime() != null)
			sql += " and livetime='"+ this.getLivetime()+"'";
		if(this.getSupport() != null)
			sql += " and support='"+ this.getSupport()+"'";
		if(this.getHouseType() != null)
			sql += " and house_type='"+ this.getHouseType()+"'";
		if(this.getDecoration() != null)
			sql += " and decoration='"+ this.getDecoration()+"'";
		if(this.getFloor() != null)
			sql += " and floor='"+ this.getFloor()+"'";
		if(this.getDirection() != null)
			sql += " and direction='"+ this.getDirection()+"'";
		if(this.getPayway() != null)
			sql += " and payway='"+ this.getPayway()+"'";
		if(this.getUpdateDate() != null)
			sql += " and update_date="+ this.getUpdateDate();
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getTelephone() != null)
			sql += " and telephone="+ this.getTelephone();
		if(this.getPerson() != null)
			sql += " and person="+ this.getPerson();
		if(this.getPictureinfo() != null)
			sql += " and pictureinfo="+ this.getPictureinfo();
		if(this.getIsCleaned() != null)
			sql += " and is_cleaned="+ this.getIsCleaned();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return Zt58PersonChuzu.class;
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
