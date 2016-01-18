package com.xiaojiang.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.helpers.LogHelper;

public class SaveRunningInfoThread extends SpiderThread {

	private Logger logger = LoggerFactory.getLogger(SaveRunningInfoThread.class);
	
	private AbstractSpider spider;
	
	public SaveRunningInfoThread(AbstractSpider spider) {
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
		
		Boolean flag = true;
		
		try {
			
			while(spider.getStatus() == AbstractSpider.RUNNING){
				
				//第一次记录爬虫启动
				if(flag == true)
				{
					logger.info(spider.getRunningUrlInfo());
					LogHelper.saveLog(spider.getId(), spider.getTaskRecordId(), null, spider.getRunningUrlInfo(), null, null, null);
					flag = false;
				}
				
				if(spider.getThreadPool().allHouseSpiderAndCleanIsWait()){
					synchronized (spider) {
						spider.wait();
					}
				}else{
					this.sleep(3000);
				}
			}
		} catch (InterruptedException e) {
			LogHelper.saveLog(spider.getId(), spider.getTaskRecordId(), "保存爬虫运行状态出错", null, null, null, null);
			logger.error("保存爬虫运行状态出错", e);
		}
	}

}
