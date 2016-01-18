package com.xiaojiang.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.helpers.LogHelper;


public class HouseCleanThread extends SpiderThread {

	private Logger logger =  LoggerFactory.getLogger(HouseCleanThread.class);
	
	private AbstractSpider spider;
	
	public HouseCleanThread(AbstractSpider spider){
		this.spider = spider;
	}
	
	@Override
	public void waitTimes() {
		
		synchronized (spider) {
			try {
				spider.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		
		spider.setCleanInfo("爬虫清洗程序启动...");
		
		logger.info(spider.getCleanInfo());
		LogHelper.saveLog(spider.getId(), spider.getTaskRecordId(), null, null, null, spider.getCleanInfo(), null);
	
		while(true){
			
			try {
			
				long d1 = System.currentTimeMillis();
				
				spider.clean();
				
				long d2 = System.currentTimeMillis();
				
				if(d2 - d1 < 60000){
					spider.setCleanInfo("爬虫清洗程序休眠中...");
					
					if(spider.getThreadPool().allHouseSpiderIsWait()){

						synchronized (spider) {
							spider.wait();
						}
						
					}else{
						Thread.sleep(10000);
					}
					
				}
				
			} catch (Exception e) {
				logger.error("清洗房源出错", e);
				LogHelper.saveLog(spider.getId(), spider.getTaskRecordId(), "清洗房源出错", null, null, spider.getCleanInfo(), null);
			}
		}
	}

	
	

}
