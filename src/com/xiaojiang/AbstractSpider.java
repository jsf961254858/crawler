package com.xiaojiang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimerTask;






















import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaojiang.bean.BeanBase;
import com.xiaojiang.bean.DataTaskProxy;
import com.xiaojiang.bean.DataTaskRecord;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.exception.ZYBException;
import com.xiaojiang.helpers.CleanHelper;
import com.xiaojiang.helpers.LogHelper;
import com.xiaojiang.httpclient.HttpClientRequestHandler;
import com.xiaojiang.proxy.ProxyPool;
import com.xiaojiang.redis.RedisService;
import com.xiaojiang.threads.HouseCleanThread;
import com.xiaojiang.threads.HouseSpiderThread;
import com.xiaojiang.threads.SaveRunningInfoThread;
import com.xiaojiang.threads.ThreadPool;
import com.xiaojiang.util.JDBCTemplate;
import com.xiaojiang.util.QueryBase;

public class AbstractSpider extends TimerTask implements Spider {

	private static final Logger logger = LoggerFactory.getLogger(AbstractSpider.class);
	
	// 爬虫url
	public static final String SPIDER_URL = "surl";
	//爬虫错误url
	public static final String SPIDER_ERROR_URL = "seurl";
	//爬虫上次抓取时间
	public static final String SPIDER_LAST_CRAWL_DATE = "slcd";
	//爬虫清洗没有匹配到楼盘的房源
	public static final String SPIDER_NOT_FIND_PROPERTY = "snfp";
	//未启动状态
	public static final int UN_RUN = 0;
	//运行状态
	public static final int RUNNING = 1;
	//休眠状态
	public static final int DORMANCY = 2;
	//抓取url结束
	public static final int URL_FINISHED = 3;
	//爬虫状态(0-未启动，1-运行中，2-休眠中)
	private int status;
	//爬虫类型(1-增量，2-全站)
	private int type;
	//总url数目
	private int urlCount;
	//无效URL数目
	private int invalidUrlCount;
	//成功写入数据库的数目
	private int successCount;
	//抓取失败数目
	private int errCount;
	//爬虫当前的运行抓取URL信息
	private String runningUrlInfo;
	//爬虫当前的运行抓取房源信息
	private String runningHouseInfo;
	//爬虫当前清洗信息
	private String cleanInfo;
	//新增页数
	private int newPageCount;
	// 当前页
	private int curPage;
	//运行时间
	private long startDate;
	//结束时间
	private long endDate;
	// 爬虫ID
	private long id;
	//分组子任务
	private List<String> groupCondition;
	//查询条件
	private String queryCondition;
	
	//运行参数
	private SpiderRunParam runParam;
	//代理池
	private ProxyPool proxyPool;
	
	private RedisService redisService;
	//线程池
	private ThreadPool threadPool;
	
	private Long taskRecordId = null;
	
	
	/**
	 * 抓取url
	 */
	@Override
	public void crawlUrl(int pageNum) throws Exception {

	}

	/**
	 * 抓取url对应的详细信息
	 */
	@Override
	public boolean crawlDetailInfo(String url) throws Exception {
		return false;
	}

	@Override
	public void run() {

		if(this.status ==  RUNNING)
			return ;
		this.status = RUNNING;
		this.startDate = System.currentTimeMillis();
		this.runningUrlInfo="爬虫程序已启动...";
		
		//第一次记录爬虫启动
		this.saveRunningInfo();
		
		logger.info(this.runningUrlInfo);
		LogHelper.saveLog(this.id, taskRecordId, null, this.runningUrlInfo, null, null, null);
		
		//初始化线程池
		this.prepareThreadPool();
		
		if(this.type == 1){//增量爬虫
			//分析新页的数量
			this.analysisNewPageCount();
			
			start(); //抓取url到redis中
			
			this.runningUrlInfo="房源URL抓取【结束】";
			
			logger.info(this.runningUrlInfo);
			LogHelper.saveLog(this.id, taskRecordId, null, this.runningUrlInfo, null, null, null);
			
			//一次运行完成
			this.oneDispatchFinished();
			
		}else if(this.type == 2){//全站分组爬虫
			
			logger.info("爬虫分组程序【开始】");
			LogHelper.saveLog(this.id, taskRecordId, null, "爬虫分组程序【开始】", null, null, null);
			
			this.analysisGroupCondition(1);
			
			logger.info("爬虫分组程序【结束】");
			LogHelper.saveLog(this.id, taskRecordId, null, "爬虫分组程序【结束】", null, null, null);
			
			if(groupCondition == null || groupCondition.size() == 0){
				this.runningUrlInfo = "爬虫程序没有找到分组条件,进入休眠状态";
				
				this.status = DORMANCY;
			}else{
				
				for(String condition : groupCondition){
					
					this.queryCondition = condition;
					
					this.analysisTotalPageCount(1);
					
					logger.info("爬虫分组获取总页数结束"+this.newPageCount);
					LogHelper.saveLog(this.id, taskRecordId, null, "爬虫分组获取总页数结束"+this.newPageCount, null, null, this.queryCondition);
					
					start();
				}
				
				this.runningUrlInfo = "房源URL抓取【结束】";
				
				logger.info(this.runningUrlInfo);
				LogHelper.saveLog(this.id, taskRecordId, null, this.runningUrlInfo, null, null, null);
				
				this.oneDispatchFinished();
			}
		}
		
		
	}
	
	
	/**
	 * 一次调度完成
	 */
	private void oneDispatchFinished() {
		
		/**
		 * 讨论：将allwait注释
		 * 不执行this.clear
		 * 修改每个爬虫的周期
		 */
		
		//threadPool.allWait();
		
		/**
		 * 判断线程池中爬取：房源的3条线程+清洗线程 是否为长休眠 
		 * 如果为长休眠则一次调度结束，否则等待
		 * 
		 * 各个线程中spider.wait执行不成功
		 * 
		 */
		while(true){
			
			if(threadPool.allHouseSpiderIsWait())
				break;
			
			try {
				Thread.sleep(1000*60*10);  //等待10min 实际项目中应该等待更长
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		LogHelper.saveLog(this.id, taskRecordId, null, "一次调度结束", null, null, null);
		logger.info("一次调度结束");
		System.out.println("一次调度结束");
		
		this.endDate = System.currentTimeMillis();
		
		this.saveRunningInfo();
		
		this.clear();
		
		this.status = URL_FINISHED;
		
		System.out.println("...end...");
	}

	private void clear() {
		this.newPageCount = 0;
		this.curPage = 0;
		this.startDate = 0;
		this.endDate = 0;
		this.invalidUrlCount = 0;
		this.urlCount = 0;
		this.errCount = 0;
		this.successCount = 0;
		this.runningHouseInfo = null;
		this.runningUrlInfo = null;
		this.cleanInfo = null;
		this.taskRecordId = null;
		this.curCleanHouseCount = 0;
		this.curCleanHouseIndex = 0;
		this.totalCleanCount = 0;
		this.setQueryCondition(null);
		this.setGroupCondition(null);
		
		//每次清空一次调度信息后，执行saveRunningInfo，保证taskRecordId不为空
		//this.saveRunningInfo();
		
	}
	/**
	 * 保存本次爬虫运行情况.
	 */
	private void saveRunningInfo() {
		
		DataTaskRecord record = new DataTaskRecord();
		
		record.setId(taskRecordId);
		record.setTaskId(id);
		record.setStartDate(startDate);
		record.setEndDate(endDate);
		record.setNewPageCount(newPageCount);
		record.setCurPage(curPage);
		record.setUrlCount(urlCount);
		record.setInvalidUrlCount(invalidUrlCount);
		record.setSuccessCount(successCount);
		record.setErrCount(errCount);
		record.setCreationDate(System.currentTimeMillis());
		record.setModificationDate(System.currentTimeMillis());
		
		taskRecordId = JDBCTemplate.save(record);
		
	}

	/**
	 * 抓取url到redis中
	 */
	private void start() {
		//如果新页为0 则结束
		if(newPageCount == 0)
			return;
		
		logger.info("房源URL抓取【开始】");
		LogHelper.saveLog(this.id, taskRecordId, null, "房源URL抓取【开始】", null, null, null);
		
		for(int i= newPageCount;i>=1;i--){
			
			//爬虫状态为不运行 退出
			if(status == UN_RUN)
				break;
			this.curPage = i;
			
			try {
				this.runningUrlInfo = "第"+i+"页房源URL抓取【开始】";
				
				//logger.info(this.runningUrlInfo);
				//LogHelper.saveLog(this.id, taskRecordId, null, this.runningUrlInfo, null, null, null);
				
				crawlUrl(i);
				
				this.runningUrlInfo = "第"+i+"页房源URL抓取【结束】";
				
				//logger.info(this.runningUrlInfo);
				//LogHelper.saveLog(this.id, taskRecordId, null, this.runningUrlInfo, null, null, null);
				
				
				//休眠 任务设置休眠时间
				this.sleeping(1);
				
			} catch (Exception e) {
				this.runningUrlInfo = "第"+i+"页房源URL抓取【失败】,原因："+e.getMessage();
				
				if(e.getMessage() != null)
				{
					if(!(e instanceof DataTaskException))
					{
						LogHelper.saveLog(this.id, taskRecordId, e.getMessage(), this.runningUrlInfo, null, null, null);
						logger.error("第"+i+"页房源URL抓取【失败】,原因："+e.getMessage(), e);
					}
				}
				
				retryCrawlUrl(i, 1);
			}
				
		}
	}

	private void retryCrawlUrl(int page, int retryNum) {
		
		this.runningUrlInfo = "第"+retryNum+"次重试抓取第"+page+"页房源URL【开始】";
		
		try {
			
			crawlUrl(page);
			
			this.sleeping(1);
			
			this.runningUrlInfo = "第"+retryNum+"次重试抓取第"+page+"页房源URL【成功】";
			/*
			if(page/newPageCount==0.1 || page/newPageCount==0.2 || page/newPageCount==0.3 || page/newPageCount==0.4 ||page/newPageCount==0.5
					||page/newPageCount==0.6 || page/newPageCount==0.7 || page/newPageCount==0.8 || page/newPageCount==0.9)
			{
				logger.info(this.runningUrlInfo);
				LogHelper.saveLog(this.id, taskRecordId, null, this.runningUrlInfo, null, null, null);
			}*/
			logger.info(this.runningUrlInfo);
			LogHelper.saveLog(this.id, taskRecordId, null, this.runningUrlInfo, null, null, null);
			
		} catch (Exception e) {
			this.runningUrlInfo = "第"+retryNum+"次重试抓取第"+page+"页房源URL【失败】,原因："+e.getMessage();
			
			if(e.getMessage() != null )
			{
				if(!(e instanceof DataTaskException))
				{
					logger.error(this.runningUrlInfo, e);
					LogHelper.saveLog(this.id, taskRecordId, null, this.runningUrlInfo, null, null, null);
				}

			}
			
			this.sleeping(1);
			
			retryNum += 1;
			
			if(retryNum >=10 )
				return;
			
			retryCrawlUrl(page, retryNum);
			
		}
		
	}

	/**
	 * 休眠
	 * @param mark 1-抓取房源url休眠，2-抓取房源信息休眠
	 */
	private void sleeping(int mark) {
		int sleepSecond = new Random().nextInt(10);
		
		if(mark == 1)
		{
			this.runningUrlInfo = "智能休眠中...";
		}
		
		if(mark == 2)
		{
			this.runningHouseInfo = "智能休眠中...";
		}
		
		long d1 = System.currentTimeMillis();
		
		long d2 = System.currentTimeMillis();
		
		long sleepInteval = sleepSecond * 1000;
		
		while((d2 - d1) < sleepInteval){
			d2 = System.currentTimeMillis();
		}
		
	}

	private void analysisNewPageCount() {
		
		this.runningUrlInfo = "爬虫程序分析新增页数【开始】";
		
		long lastCrawlDate = 0;
		//全站抓取
		if(this.runParam.isFullSiteCrawl()){//客户端指定不判断增量
			//获取上次抓取的时间
			lastCrawlDate=getLastCrawlDate();
		}
		
		//如果上次爬取的时间为0  则爬取网站的总页数 总页数在每个爬虫任务中继承
		if(lastCrawlDate == 0){
			//获取总页数
			getTotalPageCount(1);
		}else{
			//记录新页的数量
			int page = 1;
			
			//获取新页 不再是新页则跳出
			while(true){
				
				try {
					
					this.setRunningUrlInfo("增量分析第"+page+"页");
					
					//判断是否是新页  有每个爬虫任务继承实现
					boolean isNewPage = isNewPage(page, lastCrawlDate);
					
					if(isNewPage){
						page += 1;
					}else{
						this.newPageCount = page;
						break;  //跳出while
					}
					
					this.sleeping(1);
					
				} catch (Exception e) {
					
					if(e.getMessage() != null)
					{
						if(!(e instanceof DataTaskException))
						{
							LogHelper.saveLog(this.id, taskRecordId, e.getMessage(), this.runningUrlInfo, null, null, null);
							logger.error(e.getMessage(), e);
						}
					}
					
					//休眠sleepInteval 任务中设置了休眠时间久
					this.sleeping(1);
					
					continue;
				}
				
			}
		}
		this.runningUrlInfo = "爬虫程序分析新增"+this.newPageCount+"页【结束】";
		
		logger.info(this.runningUrlInfo);
		LogHelper.saveLog(this.id, taskRecordId, null, this.runningUrlInfo, null, null, null);
		
	}
	
	/**
	 * 取总页数
	 * @param num
	 */
	private void getTotalPageCount(int num) {
		
		try {
			
			this.runningUrlInfo = "第"+num+"次取总页数";
			
			this.newPageCount = totalPageCount();
			
		} catch (Exception e) {
			
			this.runningUrlInfo = "第"+num+"次取总页数【失败】，原因："+e.getMessage();
			
			if(e.getMessage() != null){
				
				if(!(e instanceof DataTaskException))
				{
					LogHelper.saveLog(this.id, taskRecordId, e.getMessage(), this.runningUrlInfo, null, null, null);
					logger.error(e.getMessage(), e);
				}
			}
			
			this.sleeping(1);
			
			getTotalPageCount(num+1);
		}
	}

	/**
	 * 取爬虫上次抓取的时间
	 */
	private long getLastCrawlDate() {
		
		try {
			String dateStr = redisService.getMapFieldValue(SPIDER_LAST_CRAWL_DATE, this.id+"");
			if(dateStr == null)
				return 0;
			return Long.valueOf(dateStr);
		} catch (ZYBException e) {
			return 0;
		}
	}
	
	/**
	 * 保存爬虫抓取时间
	 * @param date
	 */
	public void setLastCrawlDate(long date){
		try {
			redisService.setMapFieldValue(SPIDER_LAST_CRAWL_DATE, this.id+"", date+"", null);
		} catch (ZYBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化线程池
	 * 将要处理的任务，写成线程来执行
	 */
	private void prepareThreadPool(){
		
		if(threadPool != null){
			synchronized (this) {
				this.notifyAll();
			}
			return ;
		}
		
		threadPool = new ThreadPool();
		//保存运行信息线程
		threadPool.addThread(new SaveRunningInfoThread(this));  //线程池中id为0
		
		//房源清洗线程
		threadPool.addThread(new HouseCleanThread(this));    //线程池中id为1
		
		//房源爬取线程  开启3条房源抓取线程 this.runParam.getHouseSpiderThreads()   //线程池中id为2,3,4
		for(int i=0; i<this.runParam.getHouseSpiderThreads();i++){
			threadPool.addThread(new HouseSpiderThread(this));
		}
		
		threadPool.startAll();
	}
	
	
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws DataTaskException
	 */
	public Document doGet(String url)throws DataTaskException{
	
		DataTaskProxy proxy = null;
		
		try {
			
			String proxyIp = null;
			Integer proxyPort = null;
			
			if(this.runParam.isProxy()){
				proxy = proxyPool.get(this.id);
				
				if(proxy != null){
					proxyIp = proxy.getAddress();
					proxyPort = Integer.parseInt(proxy.getPort());
				}
			
			}
			
			long d1 = System.currentTimeMillis();
			
			Document doc = HttpClientRequestHandler.doGet(url, proxyIp, proxyPort);
		
			long d2 = System.currentTimeMillis();
			
			if(proxy != null)
				proxyPool.success(proxy, this.id, d2-d1);
			
			return doc;
			
		} catch (DataTaskException e) {
			
			String errorMessage = e.getMessage();
			
			boolean isTimeOut = false;
			
			if(errorMessage.contains("timed out"))
				isTimeOut = true;
			
			if(proxy != null)
				proxyPool.error(proxy, this.id, isTimeOut);
			
			//e.printStackTrace();
			throw new DataTaskException(e.getErrcode(), errorMessage, e);
		}
		//return null;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param paramaters
	 * @return
	 * @throws DataTaskException
	 */
	public Document doPost(String url, Map<String, String> paramaters)throws DataTaskException{
		
		DataTaskProxy proxy = null;
		
		try {
			
			String proxyIp = null;
			Integer proxyPort = null;
			
			if(this.runParam.isProxy()){
				proxy = proxyPool.get(this.id);

				if(proxy != null){
					proxyIp = proxy.getAddress();
					proxyPort = Integer.parseInt(proxy.getPort());
				}
			}
			
			long d1 = System.currentTimeMillis();
			
			Document doc = HttpClientRequestHandler.doPost(url, paramaters, proxyIp, proxyPort);
		
			long d2 = System.currentTimeMillis();
			
			if(proxy != null)
				proxyPool.success(proxy, this.id, d2-d1);
			
			return doc;
			
		} catch (DataTaskException e) {
			
			String errorMessage = e.getMessage();
			
			boolean isTimeOut = false;
			
			if(errorMessage.contains("timed out"))
				isTimeOut = true;
			
			if(proxy != null)
				proxyPool.error(proxy, this.id, isTimeOut);
			
			//LogHelper.saveLog(id, taskRecordId, errorMessage);
			
			throw new DataTaskException(e.getErrcode(), errorMessage, e);
		}
	}
	
	
	
	/**
	 * 保存url到redis中
	 * @param url
	 */
	public void saveUrl(String url, Long urlDate){
		try {
			
			this.runningUrlInfo = "保存URL【"+url+"】【开始】";
			
			redisService.putSet(SPIDER_URL+"-"+this.getId(), url, null);
			
			this.setUrlCount(1);
			
			this.runningUrlInfo = "保存URL【"+url+"】【结束】";
			
			if(urlDate != null){
				this.setLastCrawlDate(urlDate);
			}
			
		} catch (ZYBException e) {
			this.runningUrlInfo = "保存URL【"+url+"】【失败】";
			logger.error("保存URL【"+url+"】【失败】", e);
			LogHelper.saveLog(id, taskRecordId, "保存URL【"+url+"】【失败】",this.runningUrlInfo, null, null, url);
		
		}
	}
	
	
	private void analysisTotalPageCount(int num){
		try {
			
			this.runningUrlInfo = "爬虫分组程序第"+num+"次获取总页数";
			
			this.newPageCount = this.totalPageCount();
			
			this.runningUrlInfo = "爬虫分组获取总页数结束";
			
		} catch (Exception e) {
			
			this.runningUrlInfo = "爬虫分组程序第"+num+"次获取总页数出错,原因："+e.getMessage();
			
			this.sleeping(1);
			
			this.analysisTotalPageCount(num+1);
		}
	}
	
	private void analysisGroupCondition(int num){
		try {
			
			this.runningUrlInfo = "爬虫第"+num+"次分组程序启动...";
			
			this.group();
			
			this.runningUrlInfo = "爬虫分组结束...";
			
			
		} catch (Exception e) {
			
			this.runningUrlInfo = "爬虫第"+num+"次分组出错,原因："+e.getMessage();
			
			if(e.getMessage() != null)
			{
				if(!(e instanceof DataTaskException))
				{
					logger.error(this.runningUrlInfo, e);
					LogHelper.saveLog(this.id, taskRecordId, e.getMessage(), this.runningUrlInfo, null, null, null);
				}
			}
			
			this.sleeping(1);
			
			this.analysisGroupCondition(num+1);
		}
	}
	
	public void addGroupCondition(String condition){
		
		if(groupCondition == null)
			groupCondition = new ArrayList<String>();
		
		groupCondition.add(condition);
		
		this.runningUrlInfo = "添加了子任务【"+condition+"】";
		
	}
	

	public ProxyPool getProxyPool() {
		return proxyPool;
	}

	public void setProxyPool(ProxyPool proxyPool) {
		this.proxyPool = proxyPool;
	}

	public ThreadPool getThreadPool() {
		return threadPool;
	}

	public void setThreadPool(ThreadPool threadPool) {
		this.threadPool = threadPool;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUrlCount() {
		return urlCount;
	}

	public void setUrlCount(int urlCount) {
		this.urlCount = urlCount;
	}

	public int getInvalidUrlCount() {
		return invalidUrlCount;
	}

	public synchronized void  setInvalidUrlCount(int invalidUrlCount) {
		this.invalidUrlCount = this.invalidUrlCount + invalidUrlCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public synchronized void setSuccessCount(int successCount) {
		this.successCount = this.successCount + successCount;
	}

	public int getErrCount() {
		return errCount;
	}

	public synchronized void setErrCount(int errCount) {
		this.errCount = this.errCount + errCount;
	}

	public String getRunningUrlInfo() {
		return runningUrlInfo;
	}

	public void setRunningUrlInfo(String runningUrlInfo) {
		this.runningUrlInfo = runningUrlInfo;
	}

	public String getRunningHouseInfo() {
		return runningHouseInfo;
	}

	public void setRunningHouseInfo(String runningHouseInfo) {
		this.runningHouseInfo = runningHouseInfo;
	}

	public String getCleanInfo() {
		return cleanInfo;
	}

	public void setCleanInfo(String cleanInfo) {
		this.cleanInfo = cleanInfo;
	}

	public int getNewPageCount() {
		return newPageCount;
	}

	public void setNewPageCount(int newPageCount) {
		this.newPageCount = newPageCount;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<String> getGroupCondition() {
		return groupCondition;
	}

	public void setGroupCondition(List<String> groupCondition) {
		this.groupCondition = groupCondition;
	}

	public String getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}

	public SpiderRunParam getRunParam() {
		return runParam;
	}

	public void setRunParam(SpiderRunParam runParam) {
		this.runParam = runParam;
	}

	public RedisService getRedisService() {
		return redisService;
	}

	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}

	@Override
	public int totalPageCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isNewPage(int page, long lastCrawlDate) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stop() {
		
		this.status = UN_RUN;
		
		this.cancel();
		
		threadPool.allDestroy();
	
		this.endDate = System.currentTimeMillis();
		
		this.saveRunningInfo();
		
		this.clear();
	}

	@Override
	public void group() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public QueryBase cleanQuery = null;
	
	
	@Override
	public void clean() throws Exception {

		if(cleanQuery==null){
			return;
		}
		
		List<BeanBase> houses = CleanHelper.findList(cleanQuery);
		
		if(houses == null || houses.size() == 0){
			
			this.cleanInfo = "没有找到需要清洗的房源";
			
			return;
		}
		
		int size = houses.size();
		
		this.curCleanHouseCount = size;
		
		this.totalCleanCount += size;
		
		for(int i=0;i<size;i++){
			
			this.curCleanHouseIndex = i + 1;
			
			BeanBase entity = houses.get(i);
			
			this.cleanInfo = "清洗第"+(i+1)+"个房源";
			
			this.saveHouse(entity);
			
			this.changeCleanStatus(entity);
		}
		
	}
	
	public void changeCleanStatus(BeanBase entity){
		
		this.cleanInfo = "修改房源清洗的状态";
		
		Class clazz = entity.getClass();
		
		try {
			Method method = clazz.getDeclaredMethod("setIsCleaned", new Class[]{Boolean.class});
			method.invoke(entity, true);
			
			Method method2 = clazz.getDeclaredMethod("setModificationDate", new Class[]{Long.class});
			method2.invoke(entity, System.currentTimeMillis());
			
			JDBCTemplate.save(entity);
			
		} catch (NoSuchMethodException e) {
			logger.error("修改房源清洗的状态失败", e);
		} catch (SecurityException e) {
			logger.error("修改房源清洗的状态失败", e);
		} catch (IllegalAccessException e) {
			logger.error("修改房源清洗的状态失败", e);
		} catch (IllegalArgumentException e) {
			logger.error("修改房源清洗的状态失败", e);
		} catch (InvocationTargetException e) {
			logger.error("修改房源清洗的状态失败", e);
		}
	}
	
	public void saveHouse(BeanBase entity){
				
	}
	
	
	

	@Override
	public int getTotalCrawlHouseCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCleanHouseCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public Long getTaskRecordId() {
		return taskRecordId;
	}

	public void setTaskRecordId(Long taskRecordId) {
		this.taskRecordId = taskRecordId;
	}
	
	//本次清洗的房源数目.
	private int curCleanHouseCount;
	
	//总共清洗的房源数目.
	private int totalCleanCount;
	
	// 本次当前清洗的房源index
	private int curCleanHouseIndex;

	
	
	public int getCurCleanHouseCount() {
		return curCleanHouseCount;
	}

	public void setCurCleanHouseCount(int curCleanHouseCount) {
		this.curCleanHouseCount = curCleanHouseCount;
	}

	public int getTotalCleanCount() {
		return totalCleanCount;
	}

	public void setTotalCleanCount(int totalCleanCount) {
		this.totalCleanCount = totalCleanCount;
	}

	public int getCurCleanHouseIndex() {
		return curCleanHouseIndex;
	}

	public void setCurCleanHouseIndex(int curCleanHouseIndex) {
		this.curCleanHouseIndex = curCleanHouseIndex;
	}

	
	

}
