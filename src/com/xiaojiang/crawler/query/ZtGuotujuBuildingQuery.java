package com.xiaojiang.crawler.query;

import com.xiaojiang.crawler.bean.ZtGuotujuBuilding;
import com.xiaojiang.util.QueryBase;;

public class ZtGuotujuBuildingQuery extends QueryBase {

	private Long id;

	private Long property;

	private String buildingNumber;

	private String srcId;

	public void setId(final Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setProperty(final Long property){
		this.property = property;
	}

	public Long getProperty(){
		return this.property;
	}

	public void setBuildingNumber(final String buildingNumber){
		this.buildingNumber = buildingNumber;
	}

	public String getBuildingNumber(){
		return this.buildingNumber;
	}

	public void setSrcId(final String srcId){
		this.srcId = srcId;
	}

	public String getSrcId(){
		return this.srcId;
	}

	@Override
	public String toQuerySql(){
		String sql="select id, property, building_number, src_id from zt_guotuju_building where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getProperty() != null)
			sql += " and property="+ this.getProperty();
		if(this.getBuildingNumber() != null)
			sql += " and building_number='"+ this.getBuildingNumber()+"'";
		if(this.getSrcId() != null)
			sql += " and src_id='"+ this.getSrcId()+"'";
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
		String sql="select count(*) from zt_guotuju_building where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getProperty() != null)
			sql += " and property="+ this.getProperty();
		if(this.getBuildingNumber() != null)
			sql += " and building_number='"+ this.getBuildingNumber()+"'";
		if(this.getSrcId() != null)
			sql += " and src_id='"+ this.getSrcId()+"'";
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return ZtGuotujuBuilding.class;
	}
	@Override
	public String getDb(){
		return "source";
	}
}