package com.xiaojiang.crawlertest;

import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.httpclient.HttpClientRequestHandler;

public class happy8 {

	public void run(){
		Timer timer = new Timer();
		timer.schedule(new test(), 1000, 1000);
	}
	
	class test extends TimerTask{

		private long date2 = 0L;
		
		@Override
		public void run() {
			
			String url = "http://baidu.lecai.com/lottery/draw/view/543/726901?agentId=5563";
			try {
				crawlDetailInfo(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void crawlDetailInfo(String url) throws Exception {
			
			WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
			client.getOptions().setJavaScriptEnabled(true);    //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
			client.getOptions().setCssEnabled(false);
			client.setAjaxController(new NicelyResynchronizingAjaxController());
			client.getOptions().setThrowExceptionOnScriptError(false);        

			HtmlPage page = client.getPage(url);
			
			DomElement elementById = page.getElementById("jq_latest_draw_phase");
			
			String date1=elementById.asText();
			
			//System.out.println(date1);
			
			
			DomElement elementById2 = page.getElementById("jq_latest_draw_result");
			
			String result = elementById2.asText();
			
			//System.out.println(result);
		
			//如果当前date2
			if(Long.parseLong(date1)>date2){
				
				System.out.println("北京快乐8第"+date1+"期 "+"   "+result);
				date2=Long.parseLong(date1);
			}
			
		}
		
		
		private Document doGet(String url) throws DataTaskException {

			try {
				String proxyIp = null;
				Integer proxyPort = null;
				
				Document doc = HttpClientRequestHandler.doGet(url, proxyIp, proxyPort);
				
				return doc;
				
			} catch (DataTaskException e) {
				throw new DataTaskException(e.getErrcode(), e.getMessage(), e);
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		happy8 task = new happy8();
		task.run();
	}
	
}
