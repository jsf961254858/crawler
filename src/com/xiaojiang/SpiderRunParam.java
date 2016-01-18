package com.xiaojiang;

public class SpiderRunParam {

	private boolean isProxy;
	
	private Long sleepInteval;
	
	private int period; 
	
	private int retryCrawlHouseInfoTimes = 5;
	
	private boolean isFullSiteCrawl;
	
	private int houseSpiderThreads = 3;

	public boolean isProxy() {
		return isProxy;
	}

	public void setProxy(boolean isProxy) {
		this.isProxy = isProxy;
	}

	public Long getSleepInteval() {
		return sleepInteval;
	}

	public void setSleepInteval(Long sleepInteval) {
		this.sleepInteval = sleepInteval;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getRetryCrawlHouseInfoTimes() {
		return retryCrawlHouseInfoTimes;
	}

	public void setRetryCrawlHouseInfoTimes(int retryCrawlHouseInfoTimes) {
		this.retryCrawlHouseInfoTimes = retryCrawlHouseInfoTimes;
	}

	public boolean isFullSiteCrawl() {
		return isFullSiteCrawl;
	}

	public void setFullSiteCrawl(boolean isFullSiteCrawl) {
		this.isFullSiteCrawl = isFullSiteCrawl;
	}

	public int getHouseSpiderThreads() {
		return houseSpiderThreads;
	}

	public void setHouseSpiderThreads(int houseSpiderThreads) {
		this.houseSpiderThreads = houseSpiderThreads;
	}
	
}
