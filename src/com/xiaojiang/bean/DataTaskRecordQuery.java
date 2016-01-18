package com.xiaojiang.bean;

import com.xiaojiang.util.QueryBase;


public class DataTaskRecordQuery extends QueryBase{
	private Long id;

	private Long taskId;

	private Long startDate;

	private Long endDate;

	private Integer newPageCount;

	private Integer curPage;

	private Integer urlCount;

	private Integer invalidUrlCount;

	private Integer successCount;

	private Integer errCount;

	private Long creationDate;

	private Long modificationDate;

	public void setId(final Long id){
		this.id = id;
	}

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
	public String toQuerySql(){
		String sql="select id, task_id, start_date, end_date, new_page_count, cur_page, url_count, invalid_url_count, success_count, err_count, creation_date, modification_date from data_task_record where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getTaskId() != null)
			sql += " and task_id="+ this.getTaskId();
		if(this.getStartDate() != null)
			sql += " and start_date="+ this.getStartDate();
		if(this.getEndDate() != null)
			sql += " and end_date="+ this.getEndDate();
		if(this.getNewPageCount() != null)
			sql += " and new_page_count="+ this.getNewPageCount();
		if(this.getCurPage() != null)
			sql += " and cur_page="+ this.getCurPage();
		if(this.getUrlCount() != null)
			sql += " and url_count="+ this.getUrlCount();
		if(this.getInvalidUrlCount() != null)
			sql += " and invalid_url_count="+ this.getInvalidUrlCount();
		if(this.getSuccessCount() != null)
			sql += " and success_count="+ this.getSuccessCount();
		if(this.getErrCount() != null)
			sql += " and err_count="+ this.getErrCount();
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
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
		String sql="select count(*) from data_task_record where 1=1";

		if(this.getId() != null)
			sql += " and id="+ this.getId();
		if(this.getTaskId() != null)
			sql += " and task_id="+ this.getTaskId();
		if(this.getStartDate() != null)
			sql += " and start_date="+ this.getStartDate();
		if(this.getEndDate() != null)
			sql += " and end_date="+ this.getEndDate();
		if(this.getNewPageCount() != null)
			sql += " and new_page_count="+ this.getNewPageCount();
		if(this.getCurPage() != null)
			sql += " and cur_page="+ this.getCurPage();
		if(this.getUrlCount() != null)
			sql += " and url_count="+ this.getUrlCount();
		if(this.getInvalidUrlCount() != null)
			sql += " and invalid_url_count="+ this.getInvalidUrlCount();
		if(this.getSuccessCount() != null)
			sql += " and success_count="+ this.getSuccessCount();
		if(this.getErrCount() != null)
			sql += " and err_count="+ this.getErrCount();
		if(this.getCreationDate() != null)
			sql += " and creation_date="+ this.getCreationDate();
		if(this.getModificationDate() != null)
			sql += " and modification_date="+ this.getModificationDate();
		if(this.getCustomQueryCondotion() != null)
			sql += " and "+ this.getCustomQueryCondotion();
		return sql;
	}
	@Override
	public Class<?> getResultClass(){
		return DataTaskRecord.class;
	}
	@Override
	public String getDb(){
		return "global";
	}
}
