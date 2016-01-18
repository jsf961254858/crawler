package com.xiaojiang.bean;

import org.aspectj.lang.annotation.DeclareAnnotation;



public class DataTaskRecord implements BeanBase{

	@DeclareAnnotation("id")
	private Long id;

	@DeclareAnnotation("task_id")
	private Long taskId;

	@DeclareAnnotation("start_date")
	private Long startDate;

	@DeclareAnnotation("end_date")
	private Long endDate;

	@DeclareAnnotation("new_page_count")
	private Integer newPageCount;

	@DeclareAnnotation("cur_page")
	private Integer curPage;

	@DeclareAnnotation("url_count")
	private Integer urlCount;

	@DeclareAnnotation("invalid_url_count")
	private Integer invalidUrlCount;

	@DeclareAnnotation("success_count")
	private Integer successCount;

	@DeclareAnnotation("err_count")
	private Integer errCount;

	@DeclareAnnotation("creation_date")
	private Long creationDate;

	@DeclareAnnotation("modification_date")
	private Long modificationDate;

	public void setId(final Long id){
		this.id = id;
	}

	@Override
	public Long getId(){
		return this.id;
	}

	public void setTaskId(final Long taskId){
		this.taskId = taskId;
	}

	public Long getTaskId(){
		return this.taskId;
	}

	public void setStartDate(final Long startDate){
		this.startDate = startDate;
	}

	public Long getStartDate(){
		return this.startDate;
	}

	public void setEndDate(final Long endDate){
		this.endDate = endDate;
	}

	public Long getEndDate(){
		return this.endDate;
	}

	public void setNewPageCount(final Integer newPageCount){
		this.newPageCount = newPageCount;
	}

	public Integer getNewPageCount(){
		return this.newPageCount;
	}

	public void setCurPage(final Integer curPage){
		this.curPage = curPage;
	}

	public Integer getCurPage(){
		return this.curPage;
	}

	public void setUrlCount(final Integer urlCount){
		this.urlCount = urlCount;
	}

	public Integer getUrlCount(){
		return this.urlCount;
	}

	public void setInvalidUrlCount(final Integer invalidUrlCount){
		this.invalidUrlCount = invalidUrlCount;
	}

	public Integer getInvalidUrlCount(){
		return this.invalidUrlCount;
	}

	public void setSuccessCount(final Integer successCount){
		this.successCount = successCount;
	}

	public Integer getSuccessCount(){
		return this.successCount;
	}

	public void setErrCount(final Integer errCount){
		this.errCount = errCount;
	}

	public Integer getErrCount(){
		return this.errCount;
	}

	public void setCreationDate(final Long creationDate){
		this.creationDate = creationDate;
	}

	public Long getCreationDate(){
		return this.creationDate;
	}

	public void setModificationDate(final Long modificationDate){
		this.modificationDate = modificationDate;
	}

	public Long getModificationDate(){
		return this.modificationDate;
	}

	@Override
	public String toInsertSql(){
		String sql="insert into data_task_record ("+
		"task_id,"+
		"start_date,"+
		"end_date,"+
		"new_page_count,"+
		"cur_page,"+
		"url_count,"+
		"invalid_url_count,"+
		"success_count,"+
		"err_count,"+
		"creation_date,"+
		"modification_date"+
		") "+
		"values (";
		if(this.getTaskId() == null){
			sql += this.getTaskId()+",";
		}else{
			sql += this.getTaskId()+",";
		}
		if(this.getStartDate() == null){
			sql += this.getStartDate()+",";
		}else{
			sql += this.getStartDate()+",";
		}
		if(this.getEndDate() == null){
			sql += this.getEndDate()+",";
		}else{
			sql += this.getEndDate()+",";
		}
		if(this.getNewPageCount() == null){
			sql += this.getNewPageCount()+",";
		}else{
			sql += this.getNewPageCount()+",";
		}
		if(this.getCurPage() == null){
			sql += this.getCurPage()+",";
		}else{
			sql += this.getCurPage()+",";
		}
		if(this.getUrlCount() == null){
			sql += this.getUrlCount()+",";
		}else{
			sql += this.getUrlCount()+",";
		}
		if(this.getInvalidUrlCount() == null){
			sql += this.getInvalidUrlCount()+",";
		}else{
			sql += this.getInvalidUrlCount()+",";
		}
		if(this.getSuccessCount() == null){
			sql += this.getSuccessCount()+",";
		}else{
			sql += this.getSuccessCount()+",";
		}
		if(this.getErrCount() == null){
			sql += this.getErrCount()+",";
		}else{
			sql += this.getErrCount()+",";
		}
		if(this.getCreationDate() == null){
			sql += this.getCreationDate()+",";
		}else{
			sql += this.getCreationDate()+",";
		}
		if(this.getModificationDate() == null){
			sql += this.getModificationDate();
		}else{
			sql += this.getModificationDate();
		}
		sql += ")";
		return sql;
	}
	@Override
	public String toUpdateSql(){
		String sql="update data_task_record set ";
		if(this.getTaskId() != null) {
			sql += "task_id="+ this.getTaskId()+",";
		}
		if(this.getStartDate() != null) {
			sql += "start_date="+ this.getStartDate()+",";
		}
		if(this.getEndDate() != null) {
			sql += "end_date="+ this.getEndDate()+",";
		}
		if(this.getNewPageCount() != null) {
			sql += "new_page_count="+ this.getNewPageCount()+",";
		}
		if(this.getCurPage() != null) {
			sql += "cur_page="+ this.getCurPage()+",";
		}
		if(this.getUrlCount() != null) {
			sql += "url_count="+ this.getUrlCount()+",";
		}
		if(this.getInvalidUrlCount() != null) {
			sql += "invalid_url_count="+ this.getInvalidUrlCount()+",";
		}
		if(this.getSuccessCount() != null) {
			sql += "success_count="+ this.getSuccessCount()+",";
		}
		if(this.getErrCount() != null) {
			sql += "err_count="+ this.getErrCount()+",";
		}
		if(this.getCreationDate() != null) {
			sql += "creation_date="+ this.getCreationDate()+",";
		}
		if(this.getModificationDate() != null) {
			sql += "modification_date="+ this.getModificationDate();
		}
		if(sql.endsWith(",")){
			sql = sql.substring(0, sql.length()-1);
		}
		sql += " where id="+ this.getId();
		return sql;
	}
	@Override
	public String getDb(){
		return "global";
	}

}
