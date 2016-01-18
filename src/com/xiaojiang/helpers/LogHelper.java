package com.xiaojiang.helpers;

import com.xiaojiang.bean.RunningLog;
import com.xiaojiang.util.JDBCTemplate;


public class LogHelper {

	public static void saveLog(long spiderId, long scheduleId, 
			String message, 
			String runningUrlInfo, 
			String runningHouseInfo,
			String cleanInfo,
			String url)
	{
		
		RunningLog log = new RunningLog();
		log.setSpiderid(spiderId);
		log.setScheduleid(scheduleId);
		log.setCreationDate(System.currentTimeMillis());
		log.setReason(message);
		log.setRunningUrlInfo(runningUrlInfo);
		log.setRunningHouseInfo(runningHouseInfo);
		log.setCleanInfo(cleanInfo);
		log.setUrl(url);
		
		JDBCTemplate.save(log);
	}
	
	
}
