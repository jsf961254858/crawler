package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.CityDistrict;
import com.xiaojiang.util.QueryBase;;

public class CityDistrictQuery extends QueryBase {

	private Long id;

	private String districtName;

	private Integer zoneId;

	private Integer cityId;

	public void setId(final Long id){
		this.id = id;
	}

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
	public String toQuerySql(){
		String sql="select id, district_name, zone_id, city_id from city_district where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getDistrictName() != null)
			sql += " and district_name='"+ this.getDistrictName()+"'";
		if(this.getZoneId() != null)
			sql += " and zone_id="+ this.getZoneId();
		if(this.getCityId() != null)
			sql += " and city_id="+ this.getCityId();
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
		String sql="select count(*) from city_district where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getDistrictName() != null)
			sql += " and district_name='"+ this.getDistrictName()+"'";
		if(this.getZoneId() != null)
			sql += " and zone_id="+ this.getZoneId();
		if(this.getCityId() != null)
			sql += " and city_id="+ this.getCityId();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return CityDistrict.class;
	}
	@Override
	public String getDb(){
		return "source";
	}
}