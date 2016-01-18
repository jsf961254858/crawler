package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;
import com.xiaojiang.bean.BeanBase;;

public class DistrictKejiyuan implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("city_id")
	private Long cityId;

	@DeclareAnnotation("zone_id")
	private Long zoneId;

	@DeclareAnnotation("district_id")
	private Long districtId;

	@DeclareAnnotation("name")
	private String name;

	@DeclareAnnotation("capacity")
	private String capacity;

	@DeclareAnnotation("greenary_rate")
	private String greenaryRate;

	@DeclareAnnotation("total_house")
	private String totalHouse;

	@DeclareAnnotation("property_type")
	private String propertyType;

	@DeclareAnnotation("property_fee")
	private String propertyFee;

	@DeclareAnnotation("building_age")
	private String buildingAge;

	@DeclareAnnotation("subway")
	private String subway;

	@DeclareAnnotation("bus")
	private String bus;

	@DeclareAnnotation("hospital")
	private String hospital;

	@DeclareAnnotation("education")
	private String education;

	@DeclareAnnotation("business")
	private String business;

	@DeclareAnnotation("url")
	private String url;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
	public Long getId(){
		return this.id;
	}

	public void setCityId(final Long cityId){
		this.cityId = cityId;
	}

	public Long getCityId(){
		return this.cityId;
	}

	public void setZoneId(final Long zoneId){
		this.zoneId = zoneId;
	}

	public Long getZoneId(){
		return this.zoneId;
	}

	public void setDistrictId(final Long districtId){
		this.districtId = districtId;
	}

	public Long getDistrictId(){
		return this.districtId;
	}

	public void setName(final String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCapacity(final String capacity){
		this.capacity = capacity;
	}

	public String getCapacity(){
		return this.capacity;
	}

	public void setGreenaryRate(final String greenaryRate){
		this.greenaryRate = greenaryRate;
	}

	public String getGreenaryRate(){
		return this.greenaryRate;
	}

	public void setTotalHouse(final String totalHouse){
		this.totalHouse = totalHouse;
	}

	public String getTotalHouse(){
		return this.totalHouse;
	}

	public void setPropertyType(final String propertyType){
		this.propertyType = propertyType;
	}

	public String getPropertyType(){
		return this.propertyType;
	}

	public void setPropertyFee(final String propertyFee){
		this.propertyFee = propertyFee;
	}

	public String getPropertyFee(){
		return this.propertyFee;
	}

	public void setBuildingAge(final String buildingAge){
		this.buildingAge = buildingAge;
	}

	public String getBuildingAge(){
		return this.buildingAge;
	}

	public void setSubway(final String subway){
		this.subway = subway;
	}

	public String getSubway(){
		return this.subway;
	}

	public void setBus(final String bus){
		this.bus = bus;
	}

	public String getBus(){
		return this.bus;
	}

	public void setHospital(final String hospital){
		this.hospital = hospital;
	}

	public String getHospital(){
		return this.hospital;
	}

	public void setEducation(final String education){
		this.education = education;
	}

	public String getEducation(){
		return this.education;
	}

	public void setBusiness(final String business){
		this.business = business;
	}

	public String getBusiness(){
		return this.business;
	}

	public void setUrl(final String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	@Override
	public String toInsertSql(){
		String sql="insert into district_kejiyuan ("+
		"city_id,"+
		"zone_id,"+
		"district_id,"+
		"name,"+
		"capacity,"+
		"greenary_rate,"+
		"total_house,"+
		"property_type,"+
		"property_fee,"+
		"building_age,"+
		"subway,"+
		"bus,"+
		"hospital,"+
		"education,"+
		"business,"+
		"url"+
		") "+
		"values (";
		if(this.getCityId() == null){
			sql += this.getCityId()+",";
		}else{
			sql += this.getCityId()+",";
		}
		if(this.getZoneId() == null){
			sql += this.getZoneId()+",";
		}else{
			sql += this.getZoneId()+",";
		}
		if(this.getDistrictId() == null){
			sql += this.getDistrictId()+",";
		}else{
			sql += this.getDistrictId()+",";
		}
		if(this.getName() == null){
			sql += "'"+this.getName()+"'"+",";
		}else{
			sql += "'"+this.getName().replaceAll("'", "")+"'"+",";
		}
		if(this.getCapacity() == null){
			sql += "'"+this.getCapacity()+"'"+",";
		}else{
			sql += "'"+this.getCapacity().replaceAll("'", "")+"'"+",";
		}
		if(this.getGreenaryRate() == null){
			sql += "'"+this.getGreenaryRate()+"'"+",";
		}else{
			sql += "'"+this.getGreenaryRate().replaceAll("'", "")+"'"+",";
		}
		if(this.getTotalHouse() == null){
			sql += "'"+this.getTotalHouse()+"'"+",";
		}else{
			sql += "'"+this.getTotalHouse().replaceAll("'", "")+"'"+",";
		}
		if(this.getPropertyType() == null){
			sql += "'"+this.getPropertyType()+"'"+",";
		}else{
			sql += "'"+this.getPropertyType().replaceAll("'", "")+"'"+",";
		}
		if(this.getPropertyFee() == null){
			sql += "'"+this.getPropertyFee()+"'"+",";
		}else{
			sql += "'"+this.getPropertyFee().replaceAll("'", "")+"'"+",";
		}
		if(this.getBuildingAge() == null){
			sql += "'"+this.getBuildingAge()+"'"+",";
		}else{
			sql += "'"+this.getBuildingAge().replaceAll("'", "")+"'"+",";
		}
		if(this.getSubway() == null){
			sql += "'"+this.getSubway()+"'"+",";
		}else{
			sql += "'"+this.getSubway().replaceAll("'", "")+"'"+",";
		}
		if(this.getBus() == null){
			sql += "'"+this.getBus()+"'"+",";
		}else{
			sql += "'"+this.getBus().replaceAll("'", "")+"'"+",";
		}
		if(this.getHospital() == null){
			sql += "'"+this.getHospital()+"'"+",";
		}else{
			sql += "'"+this.getHospital().replaceAll("'", "")+"'"+",";
		}
		if(this.getEducation() == null){
			sql += "'"+this.getEducation()+"'"+",";
		}else{
			sql += "'"+this.getEducation().replaceAll("'", "")+"'"+",";
		}
		if(this.getBusiness() == null){
			sql += "'"+this.getBusiness()+"'"+",";
		}else{
			sql += "'"+this.getBusiness().replaceAll("'", "")+"'"+",";
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
		String sql="update district_kejiyuan set ";
		if(this.getCityId() != null) {
			sql += "city_id="+ this.getCityId()+",";
		}
		if(this.getZoneId() != null) {
			sql += "zone_id="+ this.getZoneId()+",";
		}
		if(this.getDistrictId() != null) {
			sql += "district_id="+ this.getDistrictId()+",";
		}
		if(this.getName() != null) {
			sql += "name='"+ this.getName().replaceAll("'", "")+"'"+",";
		}
		if(this.getCapacity() != null) {
			sql += "capacity='"+ this.getCapacity().replaceAll("'", "")+"'"+",";
		}
		if(this.getGreenaryRate() != null) {
			sql += "greenary_rate='"+ this.getGreenaryRate().replaceAll("'", "")+"'"+",";
		}
		if(this.getTotalHouse() != null) {
			sql += "total_house='"+ this.getTotalHouse().replaceAll("'", "")+"'"+",";
		}
		if(this.getPropertyType() != null) {
			sql += "property_type='"+ this.getPropertyType().replaceAll("'", "")+"'"+",";
		}
		if(this.getPropertyFee() != null) {
			sql += "property_fee='"+ this.getPropertyFee().replaceAll("'", "")+"'"+",";
		}
		if(this.getBuildingAge() != null) {
			sql += "building_age='"+ this.getBuildingAge().replaceAll("'", "")+"'"+",";
		}
		if(this.getSubway() != null) {
			sql += "subway='"+ this.getSubway().replaceAll("'", "")+"'"+",";
		}
		if(this.getBus() != null) {
			sql += "bus='"+ this.getBus().replaceAll("'", "")+"'"+",";
		}
		if(this.getHospital() != null) {
			sql += "hospital='"+ this.getHospital().replaceAll("'", "")+"'"+",";
		}
		if(this.getEducation() != null) {
			sql += "education='"+ this.getEducation().replaceAll("'", "")+"'"+",";
		}
		if(this.getBusiness() != null) {
			sql += "business='"+ this.getBusiness().replaceAll("'", "")+"'"+",";
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
		return "prediction_model";
	}
}