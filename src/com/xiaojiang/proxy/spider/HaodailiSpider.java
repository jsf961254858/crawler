package com.xiaojiang.proxy.spider;

import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaojiang.bean.DataTaskProxy;
import com.xiaojiang.bean.DataTaskProxyQuery;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.httpclient.HttpClientRequestHandler;
import com.xiaojiang.proxy.ProxyPool;
import com.xiaojiang.util.JDBCTemplate;

public class HaodailiSpider {

	private static boolean isStop = true;
	
	private ProxyPool proxyPool;
	
	
	public HaodailiSpider(ProxyPool proxyPool){
		this.proxyPool = proxyPool;
	}
	
	
	public void run(){
		Timer timer = new Timer();
		timer.schedule(new CrawlProxyTask(), 0, 1000*60*20);
	}
	
	class CrawlProxyTask extends TimerTask{

		private static final String DOMAIN = "http://www.haodailiip.com";
		
		@Override
		public void run() {
			if(isStop == false)
				return;
			isStop = false;
			crawl(1);
			
			isStop = true;
		}

		private void crawl(int page) {
			//只爬取前20页的代理
			if(page > 20)
				return;
			
			String url = DOMAIN + "/guonei/" + page;
			
			//System.out.println(url);
			
			try {
				Document doc = HttpClientRequestHandler.doGet(url, null, null);
				Element table = doc.getElementsByClass("proxy_table").get(0);
				Elements trs = table.getElementsByTag("tbody").get(0).getElementsByTag("tr");
				int size = trs.size();
				for(int i = 1; i < size; i++){
					Elements tds = trs.get(i).getElementsByTag("td");
					String ip = tds.get(0).text().trim();
					String port = tds.get(1).text().trim();
					String location = tds.get(2).text().trim();
					String protocol = tds.get(3).text().trim();
					String type = tds.get(4).text().trim();
					
					if(!protocol.contains("http") && !protocol.contains("HTTP"))
						continue;
					
					//查询获取的代理是否数据库中存在，如果存在则跳过
					DataTaskProxy proxy = get(ip,port);
					if(proxy !=null)
						continue;
					
					proxy = new DataTaskProxy();
					proxy.setIsBlack(false);
					proxy.setAddress(ip);
					proxy.setPort(port);
					proxy.setIsAnonymous(type.contains("高匿"));
					proxy.setCreationDate(System.currentTimeMillis());
					proxy.setLocation(location);
					proxy.setModificationDate(System.currentTimeMillis());
					proxy.setProtocol(protocol);
					proxy.setSuccNum(1L);
					proxy.setErrNum(0L);
					
					//加入到代理池中
					proxyPool.addNewProxy(proxy);
					
					//自行处理 放入数据库或者 写入文本
					//System.out.println(ip+"\t"+port+"\t");
				}
				//System.out.println(page);
				page++;
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				crawl(page);
			
			} catch (DataTaskException e) {
				e.printStackTrace();
			}
		}

		private DataTaskProxy get(String ip, String port) {
			DataTaskProxyQuery query = new DataTaskProxyQuery();
			query.setAddress(ip);
			query.setPort(port);
			
			return (DataTaskProxy) JDBCTemplate.findOne(query);
		}
		
	}

	public static void main(String[] args) {
		
		new HaodailiSpider(null).run();
	
	}
	
}
