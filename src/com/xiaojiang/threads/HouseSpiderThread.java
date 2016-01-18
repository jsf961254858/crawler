package com.xiaojiang.threads;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.exception.ZYBException;
import com.xiaojiang.helpers.LogHelper;

/**
 * 从redis中获取url
 * 爬起房源信息
 * @author Administrator
 *
 */
public class HouseSpiderThread extends SpiderThread{
	
	private static final Logger logger = LoggerFactory.getLogger(HouseSpiderThread.class);

	private AbstractSpider spider;
	
	private int sleepTimes = 0;
	
	public HouseSpiderThread(AbstractSpider spider){
		this.spider = spider;
	}

	@Override
	public void waitTimes() {
		
		synchronized(spider){
			try {
				
				spider.wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
	

	@Override
	public void run() {
		
		spider.setRunningHouseInfo("爬虫房源入库程序已启动...");
		
		LogHelper.saveLog(spider.getId(), spider.getTaskRecordId(), null, null, spider.getRunningHouseInfo(), null,null);
		logger.info(spider.getRunningHouseInfo());
		
		String url = null;
		String key = AbstractSpider.SPIDER_URL+"-"+spider.getId();
		String errorKey = AbstractSpider.SPIDER_ERROR_URL+"-"+spider.getId();
		
		while(spider.getStatus() != AbstractSpider.UN_RUN){
			
			try {
				url = spider.getRedisService().popSet(key);
				if(url == null){
					if(sleepTimes < 6){
						spider.setRunningHouseInfo("爬虫房源入库程序短休眠中...");
						
						sleepTimes++;
						Thread.sleep(10000);
						
					}else{
						spider.setRunningHouseInfo("爬虫房源入库程序长休眠中...");
						
						LogHelper.saveLog(spider.getId(), spider.getTaskRecordId(), null, null, spider.getRunningHouseInfo(), null,url);
						logger.info(spider.getRunningHouseInfo());
						
						sleepTimes = 0;
						
						synchronized (spider) {
							spider.wait();
						}
					}
					
				}else{
					spider.setRunningHouseInfo("抓取房源信息【开始】【"+url+"】");
					
					boolean isSuccessed = spider.crawlDetailInfo(url);
					
					spider.setRunningHouseInfo("抓取房源信息【结束】【"+url+"】");
					
					if(isSuccessed){
						spider.setSuccessCount(1);
					}else{
						spider.setInvalidUrlCount(1);
					}
				}
				Thread.sleep(new Random().nextInt(5) * 1000);
			} catch (Exception e) {
				//抓取房源失败 重试抓取
				try {
					spider.setRunningHouseInfo("抓取房源信息【失败】【"+url+"】,原因："+e.getMessage()+", 休眠中...");
					
					Thread.sleep(new Random().nextInt(10) * 1000);
					
					boolean isRetrySuccessed = retryCrawlHouseInfo(url, 1);
				
					if(!isRetrySuccessed){
						
						spider.setErrCount(1);
						
						spider.setRunningHouseInfo("房源URL【"+url+"】返回错误URL仓库【开始】");
						
						spider.getRedisService().putSet(errorKey, url, null);
						
						spider.setRunningHouseInfo("房源URL【"+url+"】返回错误URL仓库【结束】");
						
					}
				} catch (ZYBException e1) {
					spider.setRunningHouseInfo("房源URL【"+url+"】返回错误URL仓库【失败】");
					
					LogHelper.saveLog(spider.getId(), spider.getTaskRecordId(), e1.getMessage(), null, spider.getRunningHouseInfo(), null,url);
					logger.error(spider.getRunningHouseInfo());
					
					e1.printStackTrace();
				}catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 重试抓取房源信息
	 * @param page
	 * @param retryNum
	 */
	private boolean retryCrawlHouseInfo(String url, int retryNum) {
		
		
		if(retryNum > spider.getRunParam().getRetryCrawlHouseInfoTimes()){
			
			spider.setRunningHouseInfo("放弃抓取URL【"+url+"】房源信息");
			
			LogHelper.saveLog(spider.getId(), spider.getTaskRecordId(), null, null, spider.getRunningHouseInfo(), null,url);
			logger.info(spider.getRunningHouseInfo());
			
			return false;
		}
		
		spider.setRunningHouseInfo("第"+retryNum+"次重试抓取URL【"+url+"】房源信息【开始】");
		
		try {
			boolean isSuccessed = spider.crawlDetailInfo(url);
			
			if(isSuccessed)
				spider.setSuccessCount(1);
			
			spider.setRunningHouseInfo("第"+retryNum+"次重试抓取URL【"+url+"】房源信息【成功】");
			
			return true;
			
		} catch (Exception e) {
			
			spider.setRunningHouseInfo("第"+retryNum+"次重试抓取URL【"+url+"】房源信息【失败】,原因："+e.getMessage()+", 休眠中...");
			
			try {
				Thread.sleep(new Random().nextInt(10) * 1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			retryNum += 1;
		
			return retryCrawlHouseInfo(url, retryNum);
		}
	}

}
