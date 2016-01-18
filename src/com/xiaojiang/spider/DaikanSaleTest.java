package com.xiaojiang.spider;

import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.nodes.Document;

import com.xiaojiang.bean.DataTaskProxy;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.httpclient.HttpClientRequestHandler;
import com.xiaojiang.proxy.ProxyPool;

public class DaikanSaleTest {

	private static ProxyPool proxyPool;
	
	public DaikanSaleTest(ProxyPool proxyPool) {
		this.proxyPool = proxyPool;
	}

	public void run(){
		Timer timer = new Timer();
		timer.schedule(new test(), 1000*60*1, 1800000);
	}
	
	class test extends TimerTask{

		@Override
		public void run() {
			for(int i=1;i<=10;i++)
			{
				try {
					crawlUrl(i);
					
				} catch (DataTaskException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void crawlUrl(int pageNum) throws DataTaskException{
			
			String url = "http://sz.daikan.cn/sale/search0X0X0X0X0X0X0XXdefXascXdescX0?&p="+pageNum;
			
			Document doc = this.doGet(url);
			
			int size = doc.select(".hire_in1").size();
			for(int j=0;j<size;j++){
				String houseHomePageUrl = doc.select(".hire_in1").get(j).select(">dl >dt >a").attr("href");
				System.out.println(houseHomePageUrl);
			}
			
		}
		
		
		private Document doGet(String url) throws DataTaskException {

			DataTaskProxy proxy = null;
			
			try {
				String proxyIp = null;
				Integer proxyPort = null;
				
				proxy = proxyPool.get(1);//爬虫任务id为1  去代理池中获取代理
				
				if(proxy != null){
					proxyIp = proxy.getAddress();
					proxyPort = Integer.parseInt(proxy.getPort());
				}
				long d1 = System.currentTimeMillis();
				
				Document doc = HttpClientRequestHandler.doGet(url, proxyIp, proxyPort);
				
				long d2 = System.currentTimeMillis();
				//获取代理成功，
				if(proxy != null){
					proxyPool.success(proxy, 1, d2-d1);
				}
				
				return doc;
			} catch (DataTaskException e) {
				
				String errorMessage = e.getMessage();
				boolean isTimeOut = false;
				
				if(errorMessage.contains("time out"))
					isTimeOut = true;
				//应用代理是报错
				if(proxy != null){
					proxyPool.error(proxy, 1, isTimeOut);
				}
				
				throw new DataTaskException(e.getErrcode(), errorMessage, e);
			}
		}
		
	}
	
	
	
	public static void main(String[] args) throws DataTaskException {
		
		proxyPool = new ProxyPool();
		DaikanSaleTest test = new DaikanSaleTest(proxyPool);
		
		proxyPool.init();
		
		test.run();
		
	}

	

}
