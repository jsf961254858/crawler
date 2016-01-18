package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.CityCommunity;
import com.xiaojiang.util.QueryBase;;

public class CityCommunityQuery extends QueryBase {

	private Long id;

	private String communityName;

	private Long districtId;

	private Integer zoneId;

	private Integer cityId;

	private Double totalAvg;

	private Double oneMin;

	private Double oneMax;

	private Double oneAvg;

	private Double twoMin;

	private Double twoMax;

	private Double twoAvg;

	private Double thrMin;

	private Double thrMax;

	private Double thrAvg;

	private Double fouMin;

	private Double fouMax;

	private Double fouAvg;

	private Double fivMin;

	private Double fivMax;

	private Double fivAvg;

	private Long date;

	public void setId(final Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setCommunityName(final String communityName){
		this.communityName = communityName;
	}

	public String getCommunityName(){
		return this.communityName;
	}

	public void setDistrictId(final Long districtId){
		this.districtId = districtId;
	}

	public Long getDistrictId(){
		return this.districtId;
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

	public void setTotalAvg(final Double totalAvg){
		this.totalAvg = totalAvg;
	}

	public Double getTotalAvg(){
		return this.totalAvg;
	}

	public void setOneMin(final Double oneMin){
		this.oneMin = oneMin;
	}

	public Double getOneMin(){
		return this.oneMin;
	}

	public void setOneMax(final Double oneMax){
		this.oneMax = oneMax;
	}

	public Double getOneMax(){
		return this.oneMax;
	}

	public void setOneAvg(final Double oneAvg){
		this.oneAvg = oneAvg;
	}

	public Double getOneAvg(){
		return this.oneAvg;
	}

	public void setTwoMin(final Double twoMin){
		this.twoMin = twoMin;
	}

	public Double getTwoMin(){
		return this.twoMin;
	}

	public void setTwoMax(final Double twoMax){
		this.twoMax = twoMax;
	}

	public Double getTwoMax(){
		return this.twoMax;
	}

	public void setTwoAvg(final Double twoAvg){
		this.twoAvg = twoAvg;
	}

	public Double getTwoAvg(){
		return this.twoAvg;
	}

	public void setThrMin(final Double thrMin){
		this.thrMin = thrMin;
	}

	public Double getThrMin(){
		return this.thrMin;
	}

	public void setThrMax(final Double thrMax){
		this.thrMax = thrMax;
	}

	public Double getThrMax(){
		return this.thrMax;
	}

	public void setThrAvg(final Double thrAvg){
		this.thrAvg = thrAvg;
	}

	public Double getThrAvg(){
		return this.thrAvg;
	}

	public void setFouMin(final Double fouMin){
		this.fouMin = fouMin;
	}

	public Double getFouMin(){
		return this.fouMin;
	}

	public void setFouMax(final Double fouMax){
		this.fouMax = fouMax;
	}

	public Double getFouMax(){
		return this.fouMax;
	}

	public void setFouAvg(final Double fouAvg){
		this.fouAvg = fouAvg;
	}

	public Double getFouAvg(){
		return this.fouAvg;
	}

	public void setFivMin(final Double fivMin){
		this.fivMin = fivMin;
	}

	public Double getFivMin(){
		return this.fivMin;
	}

	public void setFivMax(final Double fivMax){
		this.fivMax = fivMax;
	}

	public Double getFivMax(){
		return this.fivMax;
	}

	public void setFivAvg(final Double fivAvg){
		this.fivAvg = fivAvg;
	}

	public Double getFivAvg(){
		return this.fivAvg;
	}

	public void setDate(final Long date){
		this.date = date;
	}

	public Long getDate(){
		return this.date;
	}

	@Override
	public String toQuerySql(){
		String sql="select id, community_name, district_id, zone_id, city_id, total_avg, one_min, one_max, one_avg, two_min, two_max, two_avg, thr_min, thr_max, thr_avg, fou_min, fou_max, fou_avg, fiv_min, fiv_max, fiv_avg, date from city_community where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getCommunityName() != null)
			sql += " and community_name='"+ this.getCommunityName()+"'";
		if(this.getDistrictId() != null)
			sql += " and district_id="+ this.getDistrictId();
		if(this.getZoneId() != null)
			sql += " and zone_id="+ this.getZoneId();
		if(this.getCityId() != null)
			sql += " and city_id="+ this.getCityId();
		if(this.getTotalAvg() != null)
			sql += " and total_avg="+ this.getTotalAvg();
		if(this.getOneMin() != null)
			sql += " and one_min="+ this.getOneMin();
		if(this.getOneMax() != null)
			sql += " and one_max="+ this.getOneMax();
		if(this.getOneAvg() != null)
			sql += " and one_avg="+ this.getOneAvg();
		if(this.getTwoMin() != null)
			sql += " and two_min="+ this.getTwoMin();
		if(this.getTwoMax() != null)
			sql += " and two_max="+ this.getTwoMax();
		if(this.getTwoAvg() != null)
			sql += " and two_avg="+ this.getTwoAvg();
		if(this.getThrMin() != null)
			sql += " and thr_min="+ this.getThrMin();
		if(this.getThrMax() != null)
			sql += " and thr_max="+ this.getThrMax();
		if(this.getThrAvg() != null)
			sql += " and thr_avg="+ this.getThrAvg();
		if(this.getFouMin() != null)
			sql += " and fou_min="+ this.getFouMin();
		if(this.getFouMax() != null)
			sql += " and fou_max="+ this.getFouMax();
		if(this.getFouAvg() != null)
			sql += " and fou_avg="+ this.getFouAvg();
		if(this.getFivMin() != null)
			sql += " and fiv_min="+ this.getFivMin();
		if(this.getFivMax() != null)
			sql += " and fiv_max="+ this.getFivMax();
		if(this.getFivAvg() != null)
			sql += " and fiv_avg="+ this.getFivAvg();
		if(this.getDate() != null)
			sql += " and date="+ this.getDate();
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
		String sql="select count(*) from city_community where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getCommunityName() != null)
			sql += " and community_name='"+ this.getCommunityName()+"'";
		if(this.getDistrictId() != null)
			sql += " and district_id="+ this.getDistrictId();
		if(this.getZoneId() != null)
			sql += " and zone_id="+ this.getZoneId();
		if(this.getCityId() != null)
			sql += " and city_id="+ this.getCityId();
		if(this.getTotalAvg() != null)
			sql += " and total_avg="+ this.getTotalAvg();
		if(this.getOneMin() != null)
			sql += " and one_min="+ this.getOneMin();
		if(this.getOneMax() != null)
			sql += " and one_max="+ this.getOneMax();
		if(this.getOneAvg() != null)
			sql += " and one_avg="+ this.getOneAvg();
		if(this.getTwoMin() != null)
			sql += " and two_min="+ this.getTwoMin();
		if(this.getTwoMax() != null)
			sql += " and two_max="+ this.getTwoMax();
		if(this.getTwoAvg() != null)
			sql += " and two_avg="+ this.getTwoAvg();
		if(this.getThrMin() != null)
			sql += " and thr_min="+ this.getThrMin();
		if(this.getThrMax() != null)
			sql += " and thr_max="+ this.getThrMax();
		if(this.getThrAvg() != null)
			sql += " and thr_avg="+ this.getThrAvg();
		if(this.getFouMin() != null)
			sql += " and fou_min="+ this.getFouMin();
		if(this.getFouMax() != null)
			sql += " and fou_max="+ this.getFouMax();
		if(this.getFouAvg() != null)
			sql += " and fou_avg="+ this.getFouAvg();
		if(this.getFivMin() != null)
			sql += " and fiv_min="+ this.getFivMin();
		if(this.getFivMax() != null)
			sql += " and fiv_max="+ this.getFivMax();
		if(this.getFivAvg() != null)
			sql += " and fiv_avg="+ this.getFivAvg();
		if(this.getDate() != null)
			sql += " and date="+ this.getDate();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return CityCommunity.class;
	}
	@Override
	public String getDb(){
		return "source";
	}
}