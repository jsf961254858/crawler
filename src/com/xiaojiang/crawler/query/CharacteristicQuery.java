package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.Characteristic;
import com.xiaojiang.util.QueryBase;;

public class CharacteristicQuery extends QueryBase {

	private Long id;

	private String price;

	private String area;

	private String age;

	private String orientation;

	private String floor;

	private String decoration;

	private String houseAllocation;

	private String hall;

	private String room;

	private String zone;

	private String district;

	private String community;

	private String bus;

	private String subway;

	private String education;

	private String support;

	private String managementFee;

	private String greenRate;

	private String plotRate;

	private String station;

	private String url;

	public void setId(final Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setPrice(final String price){
		this.price = price;
	}

	public String getPrice(){
		return this.price;
	}

	public void setArea(final String area){
		this.area = area;
	}

	public String getArea(){
		return this.area;
	}

	public void setAge(final String age){
		this.age = age;
	}

	public String getAge(){
		return this.age;
	}

	public void setOrientation(final String orientation){
		this.orientation = orientation;
	}

	public String getOrientation(){
		return this.orientation;
	}

	public void setFloor(final String floor){
		this.floor = floor;
	}

	public String getFloor(){
		return this.floor;
	}

	public void setDecoration(final String decoration){
		this.decoration = decoration;
	}

	public String getDecoration(){
		return this.decoration;
	}

	public void setHouseAllocation(final String houseAllocation){
		this.houseAllocation = houseAllocation;
	}

	public String getHouseAllocation(){
		return this.houseAllocation;
	}

	public void setHall(final String hall){
		this.hall = hall;
	}

	public String getHall(){
		return this.hall;
	}

	public void setRoom(final String room){
		this.room = room;
	}

	public String getRoom(){
		return this.room;
	}

	public void setZone(final String zone){
		this.zone = zone;
	}

	public String getZone(){
		return this.zone;
	}

	public void setDistrict(final String district){
		this.district = district;
	}

	public String getDistrict(){
		return this.district;
	}

	public void setCommunity(final String community){
		this.community = community;
	}

	public String getCommunity(){
		return this.community;
	}

	public void setBus(final String bus){
		this.bus = bus;
	}

	public String getBus(){
		return this.bus;
	}

	public void setSubway(final String subway){
		this.subway = subway;
	}

	public String getSubway(){
		return this.subway;
	}

	public void setEducation(final String education){
		this.education = education;
	}

	public String getEducation(){
		return this.education;
	}

	public void setSupport(final String support){
		this.support = support;
	}

	public String getSupport(){
		return this.support;
	}

	public void setManagementFee(final String managementFee){
		this.managementFee = managementFee;
	}

	public String getManagementFee(){
		return this.managementFee;
	}

	public void setGreenRate(final String greenRate){
		this.greenRate = greenRate;
	}

	public String getGreenRate(){
		return this.greenRate;
	}

	public void setPlotRate(final String plotRate){
		this.plotRate = plotRate;
	}

	public String getPlotRate(){
		return this.plotRate;
	}

	public void setStation(final String station){
		this.station = station;
	}

	public String getStation(){
		return this.station;
	}

	public void setUrl(final String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	@Override
	public String toQuerySql(){
		String sql="select id, price, area, age, orientation, floor, decoration, house_allocation, hall, room, zone, district, community, bus, subway, education, support, management_fee, green_rate, plot_rate, station, url from characteristic where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getArea() != null)
			sql += " and area='"+ this.getArea()+"'";
		if(this.getAge() != null)
			sql += " and age='"+ this.getAge()+"'";
		if(this.getOrientation() != null)
			sql += " and orientation='"+ this.getOrientation()+"'";
		if(this.getFloor() != null)
			sql += " and floor='"+ this.getFloor()+"'";
		if(this.getDecoration() != null)
			sql += " and decoration='"+ this.getDecoration()+"'";
		if(this.getHouseAllocation() != null)
			sql += " and house_allocation='"+ this.getHouseAllocation()+"'";
		if(this.getHall() != null)
			sql += " and hall='"+ this.getHall()+"'";
		if(this.getRoom() != null)
			sql += " and room='"+ this.getRoom()+"'";
		if(this.getZone() != null)
			sql += " and zone='"+ this.getZone()+"'";
		if(this.getDistrict() != null)
			sql += " and district='"+ this.getDistrict()+"'";
		if(this.getCommunity() != null)
			sql += " and community='"+ this.getCommunity()+"'";
		if(this.getBus() != null)
			sql += " and bus='"+ this.getBus()+"'";
		if(this.getSubway() != null)
			sql += " and subway='"+ this.getSubway()+"'";
		if(this.getEducation() != null)
			sql += " and education='"+ this.getEducation()+"'";
		if(this.getSupport() != null)
			sql += " and support='"+ this.getSupport()+"'";
		if(this.getManagementFee() != null)
			sql += " and management_fee='"+ this.getManagementFee()+"'";
		if(this.getGreenRate() != null)
			sql += " and green_rate='"+ this.getGreenRate()+"'";
		if(this.getPlotRate() != null)
			sql += " and plot_rate='"+ this.getPlotRate()+"'";
		if(this.getStation() != null)
			sql += " and station='"+ this.getStation()+"'";
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
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
		String sql="select count(*) from characteristic where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getPrice() != null)
			sql += " and price='"+ this.getPrice()+"'";
		if(this.getArea() != null)
			sql += " and area='"+ this.getArea()+"'";
		if(this.getAge() != null)
			sql += " and age='"+ this.getAge()+"'";
		if(this.getOrientation() != null)
			sql += " and orientation='"+ this.getOrientation()+"'";
		if(this.getFloor() != null)
			sql += " and floor='"+ this.getFloor()+"'";
		if(this.getDecoration() != null)
			sql += " and decoration='"+ this.getDecoration()+"'";
		if(this.getHouseAllocation() != null)
			sql += " and house_allocation='"+ this.getHouseAllocation()+"'";
		if(this.getHall() != null)
			sql += " and hall='"+ this.getHall()+"'";
		if(this.getRoom() != null)
			sql += " and room='"+ this.getRoom()+"'";
		if(this.getZone() != null)
			sql += " and zone='"+ this.getZone()+"'";
		if(this.getDistrict() != null)
			sql += " and district='"+ this.getDistrict()+"'";
		if(this.getCommunity() != null)
			sql += " and community='"+ this.getCommunity()+"'";
		if(this.getBus() != null)
			sql += " and bus='"+ this.getBus()+"'";
		if(this.getSubway() != null)
			sql += " and subway='"+ this.getSubway()+"'";
		if(this.getEducation() != null)
			sql += " and education='"+ this.getEducation()+"'";
		if(this.getSupport() != null)
			sql += " and support='"+ this.getSupport()+"'";
		if(this.getManagementFee() != null)
			sql += " and management_fee='"+ this.getManagementFee()+"'";
		if(this.getGreenRate() != null)
			sql += " and green_rate='"+ this.getGreenRate()+"'";
		if(this.getPlotRate() != null)
			sql += " and plot_rate='"+ this.getPlotRate()+"'";
		if(this.getStation() != null)
			sql += " and station='"+ this.getStation()+"'";
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return Characteristic.class;
	}
	@Override
	public String getDb(){
		return "source";
	}
}