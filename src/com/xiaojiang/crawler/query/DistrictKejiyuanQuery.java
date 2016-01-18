package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.DistrictKejiyuan;
import com.xiaojiang.util.QueryBase;;

public class DistrictKejiyuanQuery extends QueryBase {

	private Long id;

	private Long cityId;

	private Long zoneId;

	private Long districtId;

	private String name;

	private String capacity;

	private String greenaryRate;

	private String totalHouse;

	private String propertyType;

	private String propertyFee;

	private String buildingAge;

	private String subway;

	private String bus;

	private String hospital;

	private String education;

	private String business;

	private String url;

	public void setId(final Long id){
		this.id = id;
	}

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
	public String toQuerySql(){
		String sql="select id, city_id, zone_id, district_id, name, capacity, greenary_rate, total_house, property_type, property_fee, building_age, subway, bus, hospital, education, business, url from district_kejiyuan where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getCityId() != null)
			sql += " and city_id="+ this.getCityId();
		if(this.getZoneId() != null)
			sql += " and zone_id="+ this.getZoneId();
		if(this.getDistrictId() != null)
			sql += " and district_id="+ this.getDistrictId();
		if(this.getName() != null)
			sql += " and name='"+ this.getName()+"'";
		if(this.getCapacity() != null)
			sql += " and capacity='"+ this.getCapacity()+"'";
		if(this.getGreenaryRate() != null)
			sql += " and greenary_rate='"+ this.getGreenaryRate()+"'";
		if(this.getTotalHouse() != null)
			sql += " and total_house='"+ this.getTotalHouse()+"'";
		if(this.getPropertyType() != null)
			sql += " and property_type='"+ this.getPropertyType()+"'";
		if(this.getPropertyFee() != null)
			sql += " and property_fee='"+ this.getPropertyFee()+"'";
		if(this.getBuildingAge() != null)
			sql += " and building_age='"+ this.getBuildingAge()+"'";
		if(this.getSubway() != null)
			sql += " and subway='"+ this.getSubway()+"'";
		if(this.getBus() != null)
			sql += " and bus='"+ this.getBus()+"'";
		if(this.getHospital() != null)
			sql += " and hospital='"+ this.getHospital()+"'";
		if(this.getEducation() != null)
			sql += " and education='"+ this.getEducation()+"'";
		if(this.getBusiness() != null)
			sql += " and business='"+ this.getBusiness()+"'";
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
		String sql="select count(*) from district_kejiyuan where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getCityId() != null)
			sql += " and city_id="+ this.getCityId();
		if(this.getZoneId() != null)
			sql += " and zone_id="+ this.getZoneId();
		if(this.getDistrictId() != null)
			sql += " and district_id="+ this.getDistrictId();
		if(this.getName() != null)
			sql += " and name='"+ this.getName()+"'";
		if(this.getCapacity() != null)
			sql += " and capacity='"+ this.getCapacity()+"'";
		if(this.getGreenaryRate() != null)
			sql += " and greenary_rate='"+ this.getGreenaryRate()+"'";
		if(this.getTotalHouse() != null)
			sql += " and total_house='"+ this.getTotalHouse()+"'";
		if(this.getPropertyType() != null)
			sql += " and property_type='"+ this.getPropertyType()+"'";
		if(this.getPropertyFee() != null)
			sql += " and property_fee='"+ this.getPropertyFee()+"'";
		if(this.getBuildingAge() != null)
			sql += " and building_age='"+ this.getBuildingAge()+"'";
		if(this.getSubway() != null)
			sql += " and subway='"+ this.getSubway()+"'";
		if(this.getBus() != null)
			sql += " and bus='"+ this.getBus()+"'";
		if(this.getHospital() != null)
			sql += " and hospital='"+ this.getHospital()+"'";
		if(this.getEducation() != null)
			sql += " and education='"+ this.getEducation()+"'";
		if(this.getBusiness() != null)
			sql += " and business='"+ this.getBusiness()+"'";
		if(this.getUrl() != null)
			sql += " and url='"+ this.getUrl()+"'";
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return DistrictKejiyuan.class;
	}
	@Override
	public String getDb(){
		return "prediction_model";
	}
}