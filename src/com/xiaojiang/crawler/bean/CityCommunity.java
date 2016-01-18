package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;
import com.xiaojiang.bean.BeanBase;;

public class CityCommunity implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("community_name")
	private String communityName;

	@DeclareAnnotation("district_id")
	private Long districtId;

	@DeclareAnnotation("zone_id")
	private Integer zoneId;

	@DeclareAnnotation("city_id")
	private Integer cityId;

	@DeclareAnnotation("total_avg")
	private Double totalAvg;

	@DeclareAnnotation("one_min")
	private Double oneMin;

	@DeclareAnnotation("one_max")
	private Double oneMax;

	@DeclareAnnotation("one_avg")
	private Double oneAvg;

	@DeclareAnnotation("two_min")
	private Double twoMin;

	@DeclareAnnotation("two_max")
	private Double twoMax;

	@DeclareAnnotation("two_avg")
	private Double twoAvg;

	@DeclareAnnotation("thr_min")
	private Double thrMin;

	@DeclareAnnotation("thr_max")
	private Double thrMax;

	@DeclareAnnotation("thr_avg")
	private Double thrAvg;

	@DeclareAnnotation("fou_min")
	private Double fouMin;

	@DeclareAnnotation("fou_max")
	private Double fouMax;

	@DeclareAnnotation("fou_avg")
	private Double fouAvg;

	@DeclareAnnotation("fiv_min")
	private Double fivMin;

	@DeclareAnnotation("fiv_max")
	private Double fivMax;

	@DeclareAnnotation("fiv_avg")
	private Double fivAvg;

	@DeclareAnnotation("date")
	private Long date;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
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
	public String toInsertSql(){
		String sql="insert into city_community ("+
		"community_name,"+
		"district_id,"+
		"zone_id,"+
		"city_id,"+
		"total_avg,"+
		"one_min,"+
		"one_max,"+
		"one_avg,"+
		"two_min,"+
		"two_max,"+
		"two_avg,"+
		"thr_min,"+
		"thr_max,"+
		"thr_avg,"+
		"fou_min,"+
		"fou_max,"+
		"fou_avg,"+
		"fiv_min,"+
		"fiv_max,"+
		"fiv_avg,"+
		"date"+
		") "+
		"values (";
		if(this.getCommunityName() == null){
			sql += "'"+this.getCommunityName()+"'"+",";
		}else{
			sql += "'"+this.getCommunityName().replaceAll("'", "")+"'"+",";
		}
		if(this.getDistrictId() == null){
			sql += this.getDistrictId()+",";
		}else{
			sql += this.getDistrictId()+",";
		}
		if(this.getZoneId() == null){
			sql += this.getZoneId()+",";
		}else{
			sql += this.getZoneId()+",";
		}
		if(this.getCityId() == null){
			sql += this.getCityId()+",";
		}else{
			sql += this.getCityId()+",";
		}
		if(this.getTotalAvg() == null){
			sql += this.getTotalAvg()+",";
		}else{
			sql += this.getTotalAvg()+",";
		}
		if(this.getOneMin() == null){
			sql += this.getOneMin()+",";
		}else{
			sql += this.getOneMin()+",";
		}
		if(this.getOneMax() == null){
			sql += this.getOneMax()+",";
		}else{
			sql += this.getOneMax()+",";
		}
		if(this.getOneAvg() == null){
			sql += this.getOneAvg()+",";
		}else{
			sql += this.getOneAvg()+",";
		}
		if(this.getTwoMin() == null){
			sql += this.getTwoMin()+",";
		}else{
			sql += this.getTwoMin()+",";
		}
		if(this.getTwoMax() == null){
			sql += this.getTwoMax()+",";
		}else{
			sql += this.getTwoMax()+",";
		}
		if(this.getTwoAvg() == null){
			sql += this.getTwoAvg()+",";
		}else{
			sql += this.getTwoAvg()+",";
		}
		if(this.getThrMin() == null){
			sql += this.getThrMin()+",";
		}else{
			sql += this.getThrMin()+",";
		}
		if(this.getThrMax() == null){
			sql += this.getThrMax()+",";
		}else{
			sql += this.getThrMax()+",";
		}
		if(this.getThrAvg() == null){
			sql += this.getThrAvg()+",";
		}else{
			sql += this.getThrAvg()+",";
		}
		if(this.getFouMin() == null){
			sql += this.getFouMin()+",";
		}else{
			sql += this.getFouMin()+",";
		}
		if(this.getFouMax() == null){
			sql += this.getFouMax()+",";
		}else{
			sql += this.getFouMax()+",";
		}
		if(this.getFouAvg() == null){
			sql += this.getFouAvg()+",";
		}else{
			sql += this.getFouAvg()+",";
		}
		if(this.getFivMin() == null){
			sql += this.getFivMin()+",";
		}else{
			sql += this.getFivMin()+",";
		}
		if(this.getFivMax() == null){
			sql += this.getFivMax()+",";
		}else{
			sql += this.getFivMax()+",";
		}
		if(this.getFivAvg() == null){
			sql += this.getFivAvg()+",";
		}else{
			sql += this.getFivAvg()+",";
		}
		if(this.getDate() == null){
			sql += this.getDate();
		}else{
			sql += this.getDate();
		}
		sql += ")";
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update city_community set ";
		if(this.getCommunityName() != null) {
			sql += "community_name='"+ this.getCommunityName().replaceAll("'", "")+"'"+",";
		}
		if(this.getDistrictId() != null) {
			sql += "district_id="+ this.getDistrictId()+",";
		}
		if(this.getZoneId() != null) {
			sql += "zone_id="+ this.getZoneId()+",";
		}
		if(this.getCityId() != null) {
			sql += "city_id="+ this.getCityId()+",";
		}
		if(this.getTotalAvg() != null) {
			sql += "total_avg="+ this.getTotalAvg()+",";
		}
		if(this.getOneMin() != null) {
			sql += "one_min="+ this.getOneMin()+",";
		}
		if(this.getOneMax() != null) {
			sql += "one_max="+ this.getOneMax()+",";
		}
		if(this.getOneAvg() != null) {
			sql += "one_avg="+ this.getOneAvg()+",";
		}
		if(this.getTwoMin() != null) {
			sql += "two_min="+ this.getTwoMin()+",";
		}
		if(this.getTwoMax() != null) {
			sql += "two_max="+ this.getTwoMax()+",";
		}
		if(this.getTwoAvg() != null) {
			sql += "two_avg="+ this.getTwoAvg()+",";
		}
		if(this.getThrMin() != null) {
			sql += "thr_min="+ this.getThrMin()+",";
		}
		if(this.getThrMax() != null) {
			sql += "thr_max="+ this.getThrMax()+",";
		}
		if(this.getThrAvg() != null) {
			sql += "thr_avg="+ this.getThrAvg()+",";
		}
		if(this.getFouMin() != null) {
			sql += "fou_min="+ this.getFouMin()+",";
		}
		if(this.getFouMax() != null) {
			sql += "fou_max="+ this.getFouMax()+",";
		}
		if(this.getFouAvg() != null) {
			sql += "fou_avg="+ this.getFouAvg()+",";
		}
		if(this.getFivMin() != null) {
			sql += "fiv_min="+ this.getFivMin()+",";
		}
		if(this.getFivMax() != null) {
			sql += "fiv_max="+ this.getFivMax()+",";
		}
		if(this.getFivAvg() != null) {
			sql += "fiv_avg="+ this.getFivAvg()+",";
		}
		if(this.getDate() != null) {
			sql += "date="+ this.getDate();
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