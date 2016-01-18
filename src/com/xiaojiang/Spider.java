package com.xiaojiang;

public interface Spider {
	//爬取网页上的url
	public void crawlUrl(int pageNum) throws Exception;
	//爬取url中的详细信息
	public boolean crawlDetailInfo(String url) throws Exception;
	
	public int totalPageCount()throws Exception;
	
	public boolean isNewPage(int page, long lastCrawlDate)throws Exception;

	public void stop();

	public void group() throws Exception;
	
	public void clean()throws Exception;
	
	public int getTotalCrawlHouseCount()throws Exception;
	
	public int getTotalCleanHouseCount()throws Exception;
}
