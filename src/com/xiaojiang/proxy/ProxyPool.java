package com.xiaojiang.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;




import org.springframework.stereotype.Service;

import com.xiaojiang.bean.DataTaskProxy;
import com.xiaojiang.helpers.ProxyHelper;
import com.xiaojiang.proxy.spider.HaodailiSpider;
import com.xiaojiang.proxy.spider.Ip66Spider;
import com.xiaojiang.proxy.spider.KuaidailiSpider;
import com.xiaojiang.proxy.spider.XicidailiSpider;
import com.xiaojiang.util.JDBCTemplate;

public class ProxyPool {

	//ip池
	private List<DataTaskProxy> ipPool = new ArrayList<DataTaskProxy>();
	//超时次数
	private Map<Long, Integer> timeOutCounter = new HashMap<Long, Integer>();
	//有用的代理
	private Set<Long> usedProxy = new HashSet<Long>();
	//最大超时次数
	private static final int MAX_TIMEOUT_COUNT = 5;
	//网络禁止代理
	private WebsiteForbidProxyHandler wfph;
	//加载ip
	private Timer loadIp;
	
	public void init(){
		
		loadIp = new Timer();
		loadIp.schedule(new LoadIp(), 0, 10*60*1000);//10分钟换一批代理IP
		//开始爬取代理
		new Ip66Spider(this).run();  
		//new KuaidailiSpider(this).run();
		new HaodailiSpider(this).run();
		new XicidailiSpider(this).run();
		
		//检测代理是否可用 将不可用的拉入黑名单
		new ProxyAvailableCheckThread().run();
		
		//网站禁止代理程序
		wfph = new WebsiteForbidProxyHandler();
		
		
	}
	
	
	

	/**
	 * 获取代理
	 * @param spiderId 爬虫任务id
	 * @return proxy
	 */
	public DataTaskProxy get(long spiderId){
		
		synchronized (ipPool) {
			
			//如果代理池中没有代理  则再次加载获取代理
			if(ipPool.size() == 0)
				new LoadIp().run();
			//查找没有被禁用的代理
			DataTaskProxy proxy = findNotForbidedProxy(spiderId, 1);
			//添加到可用代理集合中
			Long proxyId = proxy.getId();
			if(proxyId != null)
				usedProxy.add(proxy.getId());
			//从代理池中删除
			ipPool.remove(proxy);

			return proxy;
		}
	}
	
	
	/**
	 * 
	 * @param spiderId 爬虫任务id
	 * @param num
	 * @return
	 */
	private DataTaskProxy findNotForbidedProxy(long spiderId, int num) {

		//在代理池中随机获取一个代理
		int index = new Random().nextInt(ipPool.size()-1);
		DataTaskProxy proxy = ipPool.get(index);
		//判断10次，要是超过10次就返回第11次获取的代理
		if(num >= 10)
			return proxy;
		//获取代理id
		Long proxyId = proxy.getId();
		//根据spiderId, proxyId 判断代理是否被网站禁用
		if(proxyId != null && wfph.isForbided(spiderId, proxyId))
			return findNotForbidedProxy(spiderId, num+1);
		
		return proxy;
	}


	public void addNewProxy(DataTaskProxy proxy){
		//同步加入池中
		synchronized (ipPool) {
			//System.out.println(ipPool.size());
			ipPool.add(proxy);
		}
	}
	
	/**
	 * 获取代理成功
	 * @param proxy 代理任务
	 * @param spiderId 爬虫任务id
	 * @param responseTime 响应时间
	 */
	public void success(DataTaskProxy proxy, long spiderId, long responseTime){
		//代理成功次数+1
		Long num = proxy.getSuccNum();
		if(num == null){
			proxy.setSuccNum(1L);
		}else{
			proxy.setSuccNum(num+1);
		}
		//保存代理到data_task_proxy表中
		long id = JDBCTemplate.save(proxy);
		proxy.setId(id);
		//从新放入代理池中
		synchronized (ipPool) {
			ipPool.add(proxy);
		}
		//保存代理id，爬虫任务id，和响应时间到data_task_proxy_time表中
		ProxyHelper.saveProxyResponseTime(proxy.getId(), spiderId, responseTime);
		
	}
	
	/**
	 * 应用代理失败
	 * @param proxy 代理任务
	 * @param spiderId	爬虫任务ID
	 * @param isTimeOut 是否超时
	 */
	public void error(DataTaskProxy proxy, long spiderId, boolean isTimeOut){
		//代理失败次数+1
		Long num = proxy.getErrNum();
		if(num == null){
			proxy.setErrNum(1L);
		}else{
			proxy.setErrNum(num+1);
		}
		//保存代理到data_task_proxy表中
		long id = JDBCTemplate.save(proxy);
		proxy.setId(id);
		//判断是否超时 如果超时则返回 否则继续
		if(isMoreThantimeOut(isTimeOut, proxy.getId()))
			return;
		
		synchronized (ipPool) {
			ipPool.add(proxy);
		}
		//如果不是超时，将代理放入禁用池中
		if(!isTimeOut)
			wfph.put(spiderId, proxy.getId());
	}
	
	
	private boolean isMoreThantimeOut(boolean isTimeOut, long proxyId){
		
		if(!isTimeOut)
			return false;
			
		Integer timeOutCount = timeOutCounter.get(proxyId);
		
		if(timeOutCount != null && timeOutCount+1 > MAX_TIMEOUT_COUNT)
			return true;
		
		if(timeOutCount == null)
			timeOutCount = 0;
		
		timeOutCounter.put(proxyId, timeOutCount+1);
		
		return false;
	}
	
	class LoadIp extends TimerTask{

		@Override
		public void run() {
			System.out.println("刷新代理池......");
			
			synchronized (ipPool) {
				//从data_task_proxy数据表中获取 可用的代理
				ipPool = ProxyHelper.refreshProxyPool(usedProxy);
			}
			
			wfph.clean();
			
			usedProxy.clear();
			
			System.out.println("代理数目:"+ipPool.size());
		}
		
	}
	
	/*
	public static void main(String[] args) {
		new ProxyPool().init();
	}
	 */

}

	
	
	
	
	