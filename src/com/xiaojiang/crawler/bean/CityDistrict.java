package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;
import com.xiaojiang.bean.BeanBase;;

public class CityDistrict implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("district_name")
	private String districtName;

	@DeclareAnnotation("zone_id")
	private Integer zoneId;

	@DeclareAnnotation("city_id")
	private Integer cityId;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
	public Long getId(){
		return this.id;
	}

	public void setDistrictName(final String districtName){
		this.districtName = districtName;
	}

	public String getDistrictName(){
		return this.districtName;
	}

	public void setZoneId(final Integer zoneId){
		this.zoneId = zoneId;
	}

	public Integer getZoneId(){
		return this.zoneId;
	}

	public void setCityId(final Integer cityId){
		this.cityId = cityId;
	}

	public Integer getCityId(){
		return this.cityId;
	}

	@Override
	public String toInsertSql(){
		String sql="insert into city_district ("+
		"district_name,"+
		"zone_id,"+
		"city_id"+
		") "+
		"values (";
		if(this.getDistrictName() == null){
			sql += "'"+this.getDistrictName()+"'"+",";
		}else{
			sql += "'"+this.getDistrictName().replaceAll("'", "")+"'"+",";
		}
		if(this.getZoneId() == null){
			sql += this.getZoneId()+",";
		}else{
			sql += this.getZoneId()+",";
		}
		if(this.getCityId() == null){
			sql += this.getCityId();
		}else{
			sql += this.getCityId();
		}
		sql += ")";
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update city_district set ";
		if(this.getDistrictName() != null) {
			sql += "district_name='"+ this.getDistrictName().replaceAll("'", "")+"'"+",";
		}
		if(this.getZoneId() != null) {
			sql += "zone_id="+ this.getZoneId()+",";
		}
		if(this.getCityId() != null) {
			sql += "city_id="+ this.getCityId();
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