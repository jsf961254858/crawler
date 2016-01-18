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



public class KuaidailiSpider {

	private static boolean isStop = true;
	
	private ProxyPool proxyPool;
	
	public KuaidailiSpider(ProxyPool proxyPool){
		this.proxyPool = proxyPool;
	}
	
	public void run(){
		
		Timer timer = new Timer();
		timer.schedule(new CrawlProxyTask(), 0, 1800000);
	}
	
	class CrawlProxyTask extends TimerTask{

		private static final String DOMAIN = "http://www.kuaidaili.com";

		@Override
		public void run() {
			
			if(isStop == false)
				return;
			
			isStop = false;
			
			crawl(1);
			
			isStop = true;
		}
		
		private void crawl(int page){
			
			if(page > 10)
				return;
			
			String url = DOMAIN + "/proxylist/"+page;
			//System.out.println(url);
			
			try {
				Document doc = HttpClientRequestHandler.doGet(url, null, null);
				Element ipList = doc.getElementById("list");
				Element table = ipList.getElementsByTag("table").get(0);
				Elements trs = table.getElementsByTag("tbody").get(0).getElementsByTag("tr");
				int size = trs.size();
		
				for(int i=0;i<size;i++){
					Elements tds = trs.get(i).getElementsByTag("td");
					
					String ip = tds.get(0).text().trim();
					
					String port = tds.get(1).text().trim();
					String location = tds.get(5).text().trim();
					String type = tds.get(2).text().trim();
					String protocol = tds.get(3).text().trim();
					
					if(protocol.contains("socks"))
						continue;
					
					//查询获取的代理是否数据库中存在，如果存在则跳过
					DataTaskProxy proxy = get(ip, port);
					if(proxy != null)
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
					//System.out.println(ip+"\t"+port+"\t"+location+"\t"+protocol+"\t"+type);
					
				}
				//System.out.println(page);
				page++;
				
				crawl(page);
				
			} catch (DataTaskException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private DataTaskProxy get(String ip, String port){
		
		DataTaskProxyQuery query = new DataTaskProxyQuery();
		query.setAddress(ip);
		query.setPort(port);
		
		return (DataTaskProxy) JDBCTemplate.findOne(query);
	}
		
	public static void main(String[] args) {
		new KuaidailiSpider(null).run();
	}
}
