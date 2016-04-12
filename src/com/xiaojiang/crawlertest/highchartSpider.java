package com.xiaojiang.crawlertest;

import java.util.List;

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
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class highchartSpider {

	
public static void crawlDetailInfo(String url) throws Exception {
		
		WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
		client.getOptions().setJavaScriptEnabled(true);    //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		client.getOptions().setThrowExceptionOnScriptError(false);   
		  
		HtmlPage page = client.getPage(url);
		
		//FrameWindow frame = page.getFrameByName("iframeDetailChart");
		
		List<FrameWindow> frames = page.getFrames();
		
		client.waitForBackgroundJavaScript(1000*10L);
		
		HtmlPage page2= (HtmlPage) frames.get(4).getEnclosedPage();
		
		Document doc = Jsoup.parse(page2.asXml());
		
		Element id = doc.getElementById("dealChartTip");
		
		Elements inputs = id.getElementsByTag("input");
		
		for(int i =0;i<inputs.size()-1;i++){
			
			String resulttest = inputs.get(i).attr("data-tip");
			Document doc1 = Jsoup.parse(resulttest);
			//System.out.println(doc1.text());
			Elements trs = doc1.getElementsByTag("tr");
			String time = "";
			String zone_name = "";
			String zone_price ="";
			String dis_name = "";
			String dis_price ="";
			String pty_name = "";
			String pty_price = "";
			
			time = trs.get(0).text();
			zone_name = trs.get(1).getElementsByTag("td").get(1).text();
			zone_price = trs.get(1).getElementsByTag("td").get(2).text();
			dis_name = trs.get(2).getElementsByTag("td").get(1).text();
			dis_price = trs.get(2).getElementsByTag("td").get(2).text();
			pty_name = trs.get(3).getElementsByTag("td").get(1).text();
			pty_price = trs.get(3).getElementsByTag("td").get(2).text();
			System.out.println(time+" "+zone_name+" "+zone_price+" "+dis_name+" "+dis_price+" "+pty_name+" "+pty_price);
		}
		

	}
	
	
	public static void main(String[] args) throws Exception {
		
		String url = "http://sz.centanet.com/xiaoqu/xq-prrdjjfibh/";
		crawlDetailInfo(url);
	}


}
