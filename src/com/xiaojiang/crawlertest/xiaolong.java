package com.xiaojiang.crawlertest;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class xiaolong {


	public static void crawlDetailInfo(String url) throws Exception {
		
		WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
		client.getOptions().setJavaScriptEnabled(true);    //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		client.getOptions().setThrowExceptionOnScriptError(false);   
		  

		HtmlPage page = client.getPage(url);
		
		DomElement elementById = page.getElementById("gameList");
		
		DomNodeList<HtmlElement> elementsByTagName = elementById.getElementsByTagName("strong");
		
		HtmlPage page2 =  elementsByTagName.get(0).click();//第一次点击比分
		
		client.waitForBackgroundJavaScript(1000*3L);  
		
		DomNode lastdiv = page2.getBody().getLastChild();//获取到弹出框   赛事直播数据
		
		//System.out.println(lastdiv.asXml());
		
		DomNode lastChild = lastdiv.getLastChild();
		
		HtmlPage page3 = ((HtmlElement) lastChild.getChildNodes().get(0).getChildNodes().get(1)).click();//第二次点击技术统计
		
		DomNode lastdiv2 = page3.getBody().getLastChild();//获取到弹出框  技术统计数据

		//System.out.println(lastdiv2.asText());
		
		String result = lastdiv2.asXml();
		
		Document doc = Jsoup.parse(result);
		
		Elements title = doc.getElementsByClass("title");
		
		System.out.println(title.get(0).text()); //获取主客队信息
		
		Elements ballStatistics = doc.getElementsByClass("ballStatistics");
		
		Elements clearfixs = ballStatistics.get(0).getElementsByClass("clearfix");
		
		for(int i=0;i<clearfixs.size();i++ ){
			System.out.println(clearfixs.get(i).text()); //获取统计信息
		}
		

	}
	
	private static void crawlDetailInfo2(String url) throws Exception{
		
		//WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
		WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
		client.getOptions().setJavaScriptEnabled(true);    //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		client.getOptions().setThrowExceptionOnScriptError(false);   
		
		client.waitForBackgroundJavaScript(1000*3L);
		
		//Thread.sleep(5000);

		HtmlPage page = client.getPage(url);
		
		Document doc = Jsoup.parse(page.asXml());
		
		//System.out.println(doc);
		
		Element element = doc.getElementsByClass("main").get(0);
		
		System.out.println(element);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		//String url = "http://live.caipiao.163.com/jcbf/?date=2015-11-08";
		//String url = "http://3g.163.com/touch/article.html?docid=BCMG21D300011229&from=sogou";
		String url = "http://www.smartisan.com/t2/#/overview";
		crawlDetailInfo2(url);
		
	}


	
	
}
