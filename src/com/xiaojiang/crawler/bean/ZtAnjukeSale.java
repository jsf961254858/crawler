package com.xiaojiang.crawler.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;

import com.xiaojiang.bean.BeanBase;

public class ZtAnjukeSale implements BeanBase {

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("spiderid")
	private Long spiderid;

	@DeclareAnnotation("scheduleid")
	private Long scheduleid;

	@DeclareAnnotation("url")
	private String url;

	@DeclareAnnotation("price")
	private String price;

	@DeclareAnnotation("ptyname")
	private String ptyname;

	@DeclareAnnotation("layout")
	private String layout;

	@DeclareAnnotation("inch")
	private String inch;

	@DeclareAnnotation("direction")
	private String direction;

	@DeclareAnnotation("unitprice")
	private String unitprice;

	@DeclareAnnotation("high")
	private String high;

	@DeclareAnnotation("type")
	private String type;

	@DeclareAnnotation("deco")
	private String deco;

	@DeclareAnnotation("description")
	private String description;

	@DeclareAnnotation("agent")
	private String agent;

	@DeclareAnnotation("tel")
	private String tel;

	@DeclareAnnotation("modification_date")
	private Long modificationDate;

	@DeclareAnnotation("creation_date")
	private Long creationDate;

	@DeclareAnnotation("is_cleaned")
	private Boolean isCleaned;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
	public Long getId(){
		return this.id;
	}

	public void setSpiderid(final Long spiderid){
		this.spiderid = spiderid;
	}

	public Long getSpiderid(){
		return this.spiderid;
	}

	public void setScheduleid(final Long scheduleid){
		this.scheduleid = scheduleid;
	}

	public Long getScheduleid(){
		return this.scheduleid;
	}

	public void setUrl(final String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public void setPrice(final String price){
		this.price = price;
	}

	public String getPrice(){
		return this.price;
	}

	public void setPtyname(final String ptyname){
		this.ptyname = ptyname;
	}

	public String getPtyname(){
		return this.ptyname;
	}

	public void setLayout(final String layout){
		this.layout = layout;
	}

	public String getLayout(){
		return this.layout;
	}

	public void setInch(final String inch){
		this.inch = inch;
	}

	public String getInch(){
		return this.inch;
	}

	public void setDirection(final String direction){
		this.direction = direction;
	}

	public String getDirection(){
		return this.direction;
	}

	public void setUnitprice(final String unitprice){
		this.unitprice = unitprice;
	}

	public String getUnitprice(){
		return this.unitprice;
	}

	public void setHigh(final String high){
		this.high = high;
	}

	public String getHigh(){
		return this.high;
	}

	public void setType(final String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setDeco(final String deco){
		this.deco = deco;
	}

	public String getDeco(){
		return this.deco;
	}

	public void setDescription(final String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setAgent(final String agent){
		this.agent = agent;
	}

	public String getAgent(){
		return this.agent;
	}

	public void setTel(final String tel){
		this.tel = tel;
	}

	public String getTel(){
		return this.tel;
	}

	public void setModificationDate(final Long modificationDate){
		this.modificationDate = modificationDate;
	}

	public Long getModificationDate(){
		return this.modificationDate;
	}

	public void setCreationDate(final Long creationDate){
		this.creationDate = creationDate;
	}

	public Long getCreationDate(){
		return this.creationDate;
	}

	public void setIsCleaned(final Boolean isCleaned){
		this.isCleaned = isCleaned;
	}

	public Boolean getIsCleaned(){
		return this.isCleaned;
	}

	@Override
	public String toInsertSql(){
		String sql="insert into zt_anjuke_sale ("+
		"spiderid,"+
		"scheduleid,"+
		"url,"+
		"price,"+
		"ptyname,"+
		"layout,"+
		"inch,"+
		"direction,"+
		"unitprice,"+
		"high,"+
		"type,"+
		"deco,"+
		"description,"+
		"agent,"+
		"tel,"+
		"modification_date,"+
		"creation_date,"+
		"is_cleaned"+
		") "+
		"values (";
		if(this.getSpiderid() == null){
			sql += this.getSpiderid()+",";
		}else{
			sql += this.getSpiderid()+",";
		}
		if(this.getScheduleid() == null){
			sql += this.getScheduleid()+",";
		}else{
			sql += this.getScheduleid()+",";
		}
		if(this.getUrl() == null){
			sql += "'"+this.getUrl()+"'"+",";
		}else{
			sql += "'"+this.getUrl().replaceAll("'", "")+"'"+",";
		}
		if(this.getPrice() == null){
			sql += "'"+this.getPrice()+"'"+",";
		}else{
			sql += "'"+this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getPtyname() == null){
			sql += "'"+this.getPtyname()+"'"+",";
		}else{
			sql += "'"+this.getPtyname().replaceAll("'", "")+"'"+",";
		}
		if(this.getLayout() == null){
			sql += "'"+this.getLayout()+"'"+",";
		}else{
			sql += "'"+this.getLayout().replaceAll("'", "")+"'"+",";
		}
		if(this.getInch() == null){
			sql += "'"+this.getInch()+"'"+",";
		}else{
			sql += "'"+this.getInch().replaceAll("'", "")+"'"+",";
		}
		if(this.getDirection() == null){
			sql += "'"+this.getDirection()+"'"+",";
		}else{
			sql += "'"+this.getDirection().replaceAll("'", "")+"'"+",";
		}
		if(this.getUnitprice() == null){
			sql += "'"+this.getUnitprice()+"'"+",";
		}else{
			sql += "'"+this.getUnitprice().replaceAll("'", "")+"'"+",";
		}
		if(this.getHigh() == null){
			sql += "'"+this.getHigh()+"'"+",";
		}else{
			sql += "'"+this.getHigh().replaceAll("'", "")+"'"+",";
		}
		if(this.getType() == null){
			sql += "'"+this.getType()+"'"+",";
		}else{
			sql += "'"+this.getType().replaceAll("'", "")+"'"+",";
		}
		if(this.getDeco() == null){
			sql += "'"+this.getDeco()+"'"+",";
		}else{
			sql += "'"+this.getDeco().replaceAll("'", "")+"'"+",";
		}
		if(this.getDescription() == null){
			sql += "'"+this.getDescription()+"'"+",";
		}else{
			sql += "'"+this.getDescription().replaceAll("'", "")+"'"+",";
		}
		if(this.getAgent() == null){
			sql += "'"+this.getAgent()+"'"+",";
		}else{
			sql += "'"+this.getAgent().replaceAll("'", "")+"'"+",";
		}
		if(this.getTel() == null){
			sql += "'"+this.getTel()+"'"+",";
		}else{
			sql += "'"+this.getTel().replaceAll("'", "")+"'"+",";
		}
		if(this.getModificationDate() == null){
			sql += this.getModificationDate()+",";
		}else{
			sql += this.getModificationDate()+",";
		}
		if(this.getCreationDate() == null){
			sql += this.getCreationDate()+",";
		}else{
			sql += this.getCreationDate()+",";
		}
		if(this.getIsCleaned() == null){
			sql += this.getIsCleaned();
		}else{
			sql += this.getIsCleaned();
		}
		sql += ")";
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update zt_anjuke_sale set ";
		if(this.getSpiderid() != null) {
			sql += "spiderid="+ this.getSpiderid()+",";
		}
		if(this.getScheduleid() != null) {
			sql += "scheduleid="+ this.getScheduleid()+",";
		}
		if(this.getUrl() != null) {
			sql += "url='"+ this.getUrl().replaceAll("'", "")+"'"+",";
		}
		if(this.getPrice() != null) {
			sql += "price='"+ this.getPrice().replaceAll("'", "")+"'"+",";
		}
		if(this.getPtyname() != null) {
			sql += "ptyname='"+ this.getPtyname().replaceAll("'", "")+"'"+",";
		}
		if(this.getLayout() != null) {
			sql += "layout='"+ this.getLayout().replaceAll("'", "")+"'"+",";
		}
		if(this.getInch() != null) {
			sql += "inch='"+ this.getInch().replaceAll("'", "")+"'"+",";
		}
		if(this.getDirection() != null) {
			sql += "direction='"+ this.getDirection().replaceAll("'", "")+"'"+",";
		}
		if(this.getUnitprice() != null) {
			sql += "unitprice='"+ this.getUnitprice().replaceAll("'", "")+"'"+",";
		}
		if(this.getHigh() != null) {
			sql += "high='"+ this.getHigh().replaceAll("'", "")+"'"+",";
		}
		if(this.getType() != null) {
			sql += "type='"+ this.getType().replaceAll("'", "")+"'"+",";
		}
		if(this.getDeco() != null) {
			sql += "deco='"+ this.getDeco().replaceAll("'", "")+"'"+",";
		}
		if(this.getDescription() != null) {
			sql += "description='"+ this.getDescription().replaceAll("'", "")+"'"+",";
		}
		if(this.getAgent() != null) {
			sql += "agent='"+ this.getAgent().replaceAll("'", "")+"'"+",";
		}
		if(this.getTel() != null) {
			sql += "tel='"+ this.getTel().replaceAll("'", "")+"'"+",";
		}
		if(this.getModificationDate() != null) {
			sql += "modification_date="+ this.getModificationDate()+",";
		}
		if(this.getCreationDate() != null) {
			sql += "creation_date="+ this.getCreationDate()+",";
		}
		if(this.getIsCleaned() != null) {
			sql += "is_cleaned="+ this.getIsCleaned();
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