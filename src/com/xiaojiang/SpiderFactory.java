package com.xiaojiang;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.Context;

import com.xiaojiang.bean.DataTask;
import com.xiaojiang.bean.DataTaskQuery;
import com.xiaojiang.crawler.AnjukeCommunityInfoSpider;
import com.xiaojiang.crawler.AnjukeSaleSpiderTask;
import com.xiaojiang.crawler.DaikanSaleTest;
import com.xiaojiang.crawler.FangRentSpiderTask;
import com.xiaojiang.crawler.FangChuzuAgentInfo;
import com.xiaojiang.crawler.Five8ChuzuAgentInfo;
import com.xiaojiang.crawler.Five8PersonChuzuGroupSpider;
import com.xiaojiang.crawler.Five8PersonChuzuSpider;
import com.xiaojiang.crawler.GuoTuJuBuildingSpiderTask;
import com.xiaojiang.exception.ParameterException;
import com.xiaojiang.exception.RedisException;
import com.xiaojiang.exception.ZYBException;
import com.xiaojiang.proxy.ProxyPool;
import com.xiaojiang.redis.RedisService;
import com.xiaojiang.util.JDBCTemplate;

@Service
public class SpiderFactory {


	//private static RedisService redisService;
	
	//private static ProxyPool proxyPool;
	@Autowired
	private RedisService redisService;

	@Autowired
	private ProxyPool proxyPool;
	//使用map保存爬虫任务到spiders中
	private Map<Long,Spider> spiders = new HashMap<Long, Spider>();
	
	
	public void run(long id)throws Exception{
		
		start(id, null);
	}
	
	public void stop(long id)throws Exception{
		
		AbstractSpider spider = (AbstractSpider) spiders.get(id);
		
		if(spider == null || spider.getStatus() == 0)
			throw new RedisException("爬虫未启动", null);
		
		spider.stop();
		
		spiders.remove(id);
	}

	/**
	 * 启动入口
	 * @param id 爬虫任务id
	 * @param taskRecordId 任务记录id为 null
	 * @throws ZYBException
	 */
	private void start(long id, Long taskRecordId) throws ZYBException{
		//根据id获取爬虫任务
		DataTask dataTask = getById(id);
		
		//此处应该抛弃其他错误，而不是redisException,为了简写
		if(dataTask == null)
			throw new RedisException("爬虫不存在", null); 
	
		AbstractSpider spider = (AbstractSpider) spiders.get(id);
		
		if(spider == null)
			spider = initSpider(dataTask, taskRecordId);
		
		if(spider.getStatus() == 1)
			ParameterException.parameterConflictError("爬虫正在运行中，不能重复启动");
		
		if(spider.getStatus() == 2)
			ParameterException.parameterConflictError("爬虫正在休眠中，不能重复启动");
	
		//启动爬虫，延迟5s，爬虫运行周期为getPeriod() min
		Timer timer = new Timer();
		timer.schedule(spider, 5000, dataTask.getPeriod() * 60*1000);
		
		//将爬虫任务放入spiders中 以后就不用每次去初始化
		spiders.put(dataTask.getId(), spider);
	}

	
	private DataTask getById(long id) {

		DataTaskQuery query = new DataTaskQuery();
		query.setId(id);
	
		DataTask datatask = (DataTask) JDBCTemplate.findOne(query);
		return datatask;
	}

	/**
	 * 初始化爬虫
	 * @param dataTask
	 * @return
	 */
	private AbstractSpider initSpider(DataTask dataTask, Long taskRecordId) throws ZYBException{
		
		long id = dataTask.getId();
		
		AbstractSpider spider = null;
		
		//根据爬虫任务Id，初始化响应的爬虫任务
		if(id == 37){
    		spider = new DaikanSaleTest();
    	}else if(id == 4550){
    		spider = new Five8PersonChuzuSpider();
    	}else if(id == 4530){
    		spider = new Five8PersonChuzuGroupSpider();
    	}else if(id == 33){
    		spider = new AnjukeSaleSpiderTask();
    	}else if (id == 21 ){
    		spider = new FangRentSpiderTask();
    	}else if(id == 9113174){
    		spider = new FangChuzuAgentInfo();
    	}else if(id == 9113175){
    		spider = new Five8ChuzuAgentInfo();
    	}else if(id == 8989521){
    		spider = new GuoTuJuBuildingSpiderTask();
    	}else if(id == 20151228){
    		spider = new AnjukeCommunityInfoSpider();
    	}
    		
		
    		
		
		//此处应该抛弃其他错误，而不是redisException,为了简写
		if(spider == null)
			throw new RedisException("初始化爬虫失败", null);
		
		//设置爬虫运行参数
		SpiderRunParam runParam = new SpiderRunParam();
		runParam.setProxy(dataTask.getIsproxy());
		runParam.setPeriod(dataTask.getPeriod());
		runParam.setSleepInteval(5000L);
		
		spider.setId(id);
		spider.setType(dataTask.getTasktype());
		spider.setTaskRecordId(taskRecordId);
		spider.setRunParam(runParam);
		spider.setRedisService(redisService);
		spider.setProxyPool(proxyPool);

		return spider;
	}

	
	

	public Map<Long, Spider> getSpiders() {
		return spiders;
	}

	public void setSpiders(Map<Long, Spider> spiders) {
		this.spiders = spiders;
	}

	/**
	 * 测试入口
	 * 主要是启动代理池 和 redis服务
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		SpiderFactory test = new SpiderFactory();
		
		test.run(37);
	}

	
	/**
	 * 构造函数 实现sping注入 依赖注入测试中
	 * 
	 */
	private ClassPathXmlApplicationContext context;
	
	public SpiderFactory(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		RedisService rs = (RedisService) context.getBean("redisService");
		ProxyPool pool = (ProxyPool) context.getBean("proxyPool");
		
		this.setRedisService(rs);
		this.setProxyPool(pool);
		
		context.start();	
	}

	public RedisService getRedisService() {
		return redisService;
	}

	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}

	public ProxyPool getProxyPool() {
		return proxyPool;
	}

	public void setProxyPool(ProxyPool proxyPool) {
		this.proxyPool = proxyPool;
	}
	
}
