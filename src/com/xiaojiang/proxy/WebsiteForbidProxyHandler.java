package com.xiaojiang.proxy;

import java.util.HashMap;
import java.util.Map;

public class WebsiteForbidProxyHandler {

	//禁用代理Map<spiderId,map<proxyId,System.currentTimeMillis()>>
	private Map<Long, Map<Long, Long>> forbidProxy = new HashMap<Long, Map<Long,Long>>();
	
	
	public void put(long spiderId, long proxyId){
		
		synchronized (forbidProxy) {
			
			Map<Long, Long> proxy = forbidProxy.get(spiderId);
			
			if(proxy == null){
				proxy = new HashMap<Long, Long>();
				proxy.put(proxyId, System.currentTimeMillis());
			}else{
				if(!proxy.containsKey(proxyId)){
					proxy.put(proxyId, System.currentTimeMillis());
				}
			}
			
			forbidProxy.put(spiderId, proxy);
		}
	}
	/**
	 * 根据爬虫任务的id和代理的id 代理是否被网站禁用
	 * @param spiderId
	 * @param proxyId
	 * @return
	 */
	public boolean isForbided(long spiderId, long proxyId){
		
		synchronized (forbidProxy) {
			Map<Long, Long> proxy = forbidProxy.get(spiderId);
			if(proxy == null || !proxy.containsKey(proxyId))
				return false;
			return true;
		}
	}
	
	public void clean(){
		
		synchronized (forbidProxy) {
			forbidProxy.clear();
		}
	}
	
}
