package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;
import com.xiaojiang.bean.BeanBase;;

public class ZtGuotujuBuilding implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("property")
	private Long property;

	@DeclareAnnotation("building_number")
	private String buildingNumber;

	@DeclareAnnotation("src_id")
	private String srcId;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
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
	public String toInsertSql(){
		String sql="insert into zt_guotuju_building ("+
		"property,"+
		"building_number,"+
		"src_id"+
		") "+
		"values (";
		if(this.getProperty() == null){
			sql += this.getProperty()+",";
		}else{
			sql += this.getProperty()+",";
		}
		if(this.getBuildingNumber() == null){
			sql += "'"+this.getBuildingNumber()+"'"+",";
		}else{
			sql += "'"+this.getBuildingNumber().replaceAll("'", "")+"'"+",";
		}
		if(this.getSrcId() == null){
			sql += "'"+this.getSrcId()+"'";
		}else{
			sql += "'"+this.getSrcId().replaceAll("'", "")+"'";
		}
		sql += ")";
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update zt_guotuju_building set ";
		if(this.getProperty() != null) {
			sql += "property="+ this.getProperty()+",";
		}
		if(this.getBuildingNumber() != null) {
			sql += "building_number='"+ this.getBuildingNumber().replaceAll("'", "")+"'"+",";
		}
		if(this.getSrcId() != null) {
			sql += "src_id='"+ this.getSrcId().replaceAll("'", "")+"'";
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