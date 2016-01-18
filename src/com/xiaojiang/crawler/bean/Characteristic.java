package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;
import com.xiaojiang.bean.BeanBase;;

public class Characteristic implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("price")
	private String price;

	@DeclareAnnotation("area")
	private String area;

	@DeclareAnnotation("age")
	private String age;

	@DeclareAnnotation("orientation")
	private String orientation;

	@DeclareAnnotation("floor")
	private String floor;

	@DeclareAnnotation("decoration")
	private String decoration;

	@DeclareAnnotation("house_allocation")
	private String houseAllocation;

	@DeclareAnnotation("hall")
	private String hall;

	@DeclareAnnotation("room")
	private String room;

	@DeclareAnnotation("zone")
	private String zone;

	@DeclareAnnotation("district")
	private String district;

	@DeclareAnnotation("community")
	private String community;

	@DeclareAnnotation("bus")
	private String bus;

	@DeclareAnnotation("subway")
	private String subway;

	@DeclareAnnotation("education")
	private String education;

	@DeclareAnnotation("support")
	private String support;

	@DeclareAnnotation("management_fee")
	private String managementFee;

	@DeclareAnnotation("green_rate")
	private String greenRate;

	@DeclareAnnotation("plot_rate")
	private String plotRate;

	@DeclareAnnotation("station")
	private String station;

	@DeclareAnnotation("url")
	private String url;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
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
	public String toInsertSql(){
		String sql="insert into characteristic ("+
		"price,"+
		"area,"+
		"age,"+
		"orientation,"+
		"floor,"+
		"decoration,"+
		"house_allocation,"+
		"hall,"+
		"room,"+
		"zone,"+
		"district,"+
		"community,"+
		"bus,"+
		"subway,"+
		"education,"+
		"support,"+
		"management_fee,"+
		"green_rate,"+
		"plot_rate,"+
		"station,"+
		"url"+
		") "+
		"values (";
		if(this.getPrice() == null){
			sql += "'"+this.getPrice()+"'"+",";
		}else{
			sql += "'"+this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getArea() == null){
			sql += "'"+this.getArea()+"'"+",";
		}else{
			sql += "'"+this.getArea().replaceAll("'", "")+"'"+",";
		}
		if(this.getAge() == null){
			sql += "'"+this.getAge()+"'"+",";
		}else{
			sql += "'"+this.getAge().replaceAll("'", "")+"'"+",";
		}
		if(this.getOrientation() == null){
			sql += "'"+this.getOrientation()+"'"+",";
		}else{
			sql += "'"+this.getOrientation().replaceAll("'", "")+"'"+",";
		}
		if(this.getFloor() == null){
			sql += "'"+this.getFloor()+"'"+",";
		}else{
			sql += "'"+this.getFloor().replaceAll("'", "")+"'"+",";
		}
		if(this.getDecoration() == null){
			sql += "'"+this.getDecoration()+"'"+",";
		}else{
			sql += "'"+this.getDecoration().replaceAll("'", "")+"'"+",";
		}
		if(this.getHouseAllocation() == null){
			sql += "'"+this.getHouseAllocation()+"'"+",";
		}else{
			sql += "'"+this.getHouseAllocation().replaceAll("'", "")+"'"+",";
		}
		if(this.getHall() == null){
			sql += "'"+this.getHall()+"'"+",";
		}else{
			sql += "'"+this.getHall().replaceAll("'", "")+"'"+",";
		}
		if(this.getRoom() == null){
			sql += "'"+this.getRoom()+"'"+",";
		}else{
			sql += "'"+this.getRoom().replaceAll("'", "")+"'"+",";
		}
		if(this.getZone() == null){
			sql += "'"+this.getZone()+"'"+",";
		}else{
			sql += "'"+this.getZone().replaceAll("'", "")+"'"+",";
		}
		if(this.getDistrict() == null){
			sql += "'"+this.getDistrict()+"'"+",";
		}else{
			sql += "'"+this.getDistrict().replaceAll("'", "")+"'"+",";
		}
		if(this.getCommunity() == null){
			sql += "'"+this.getCommunity()+"'"+",";
		}else{
			sql += "'"+this.getCommunity().replaceAll("'", "")+"'"+",";
		}
		if(this.getBus() == null){
			sql += "'"+this.getBus()+"'"+",";
		}else{
			sql += "'"+this.getBus().replaceAll("'", "")+"'"+",";
		}
		if(this.getSubway() == null){
			sql += "'"+this.getSubway()+"'"+",";
		}else{
			sql += "'"+this.getSubway().replaceAll("'", "")+"'"+",";
		}
		if(this.getEducation() == null){
			sql += "'"+this.getEducation()+"'"+",";
		}else{
			sql += "'"+this.getEducation().replaceAll("'", "")+"'"+",";
		}
		if(this.getSupport() == null){
			sql += "'"+this.getSupport()+"'"+",";
		}else{
			sql += "'"+this.getSupport().replaceAll("'", "")+"'"+",";
		}
		if(this.getManagementFee() == null){
			sql += "'"+this.getManagementFee()+"'"+",";
		}else{
			sql += "'"+this.getManagementFee().replaceAll("'", "")+"'"+",";
		}
		if(this.getGreenRate() == null){
			sql += "'"+this.getGreenRate()+"'"+",";
		}else{
			sql += "'"+this.getGreenRate().replaceAll("'", "")+"'"+",";
		}
		if(this.getPlotRate() == null){
			sql += "'"+this.getPlotRate()+"'"+",";
		}else{
			sql += "'"+this.getPlotRate().replaceAll("'", "")+"'"+",";
		}
		if(this.getStation() == null){
			sql += "'"+this.getStation()+"'"+",";
		}else{
			sql += "'"+this.getStation().replaceAll("'", "")+"'"+",";
		}
		if(this.getUrl() == null){
			sql += "'"+this.getUrl()+"'";
		}else{
			sql += "'"+this.getUrl().replaceAll("'", "")+"'";
		}
		sql += ")";
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update characteristic set ";
		if(this.getPrice() != null) {
			sql += "price='"+ this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getArea() != null) {
			sql += "area='"+ this.getArea().replaceAll("'", "")+"'"+",";
		}
		if(this.getAge() != null) {
			sql += "age='"+ this.getAge().replaceAll("'", "")+"'"+",";
		}
		if(this.getOrientation() != null) {
			sql += "orientation='"+ this.getOrientation().replaceAll("'", "")+"'"+",";
		}
		if(this.getFloor() != null) {
			sql += "floor='"+ this.getFloor().replaceAll("'", "")+"'"+",";
		}
		if(this.getDecoration() != null) {
			sql += "decoration='"+ this.getDecoration().replaceAll("'", "")+"'"+",";
		}
		if(this.getHouseAllocation() != null) {
			sql += "house_allocation='"+ this.getHouseAllocation().replaceAll("'", "")+"'"+",";
		}
		if(this.getHall() != null) {
			sql += "hall='"+ this.getHall().replaceAll("'", "")+"'"+",";
		}
		if(this.getRoom() != null) {
			sql += "room='"+ this.getRoom().replaceAll("'", "")+"'"+",";
		}
		if(this.getZone() != null) {
			sql += "zone='"+ this.getZone().replaceAll("'", "")+"'"+",";
		}
		if(this.getDistrict() != null) {
			sql += "district='"+ this.getDistrict().replaceAll("'", "")+"'"+",";
		}
		if(this.getCommunity() != null) {
			sql += "community='"+ this.getCommunity().replaceAll("'", "")+"'"+",";
		}
		if(this.getBus() != null) {
			sql += "bus='"+ this.getBus().replaceAll("'", "")+"'"+",";
		}
		if(this.getSubway() != null) {
			sql += "subway='"+ this.getSubway().replaceAll("'", "")+"'"+",";
		}
		if(this.getEducation() != null) {
			sql += "education='"+ this.getEducation().replaceAll("'", "")+"'"+",";
		}
		if(this.getSupport() != null) {
			sql += "support='"+ this.getSupport().replaceAll("'", "")+"'"+",";
		}
		if(this.getManagementFee() != null) {
			sql += "management_fee='"+ this.getManagementFee().replaceAll("'", "")+"'"+",";
		}
		if(this.getGreenRate() != null) {
			sql += "green_rate='"+ this.getGreenRate().replaceAll("'", "")+"'"+",";
		}
		if(this.getPlotRate() != null) {
			sql += "plot_rate='"+ this.getPlotRate().replaceAll("'", "")+"'"+",";
		}
		if(this.getStation() != null) {
			sql += "station='"+ this.getStation().replaceAll("'", "")+"'"+",";
		}
		if(this.getUrl() != null) {
			sql += "url='"+ this.getUrl().replaceAll("'", "")+"'";
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