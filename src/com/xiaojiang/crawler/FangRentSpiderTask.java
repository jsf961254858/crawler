package com.xiaojiang.crawler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;
import com.xiaojiang.crawler.bean.ZtFangRent;
import com.xiaojiang.crawler.query.ZtFangRentQuery;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.util.JDBCTemplate;



public class FangRentSpiderTask  extends AbstractSpider {
	
	
	@Override
	public void crawlUrl(int pageNum) throws Exception {
		String url = "http://zu.sz.fang.com/house-a087/i3"+pageNum+"/";
		
		Document doc = this.doGet(url);
		
		int size = doc.select(".floatr .title >a").size();
		for(int j=0;j<size;j++){
			String houseHomePageUrl = "http://zu.sz.fang.com"+doc.select(".floatr .title >a").get(j).attr("href");
			this.saveUrl(houseHomePageUrl, 0L);
			//System.out.println(houseHomePageUrl);
		}
		
	}
	

	
	private ZtFangRent isCrawed(String url){
		ZtFangRentQuery query = new ZtFangRentQuery();
		query.setUrl(url);
		
		return (ZtFangRent) JDBCTemplate.findOne(query);
	}
	
	@Override
	public boolean crawlDetailInfo(String url) throws Exception {

		Document doc = this.doGet(url);

		ZtFangRent ztFangRent = isCrawed(url);
		if(ztFangRent == null)
			ztFangRent = new ZtFangRent();
		
		ztFangRent.setUrl(url);
		
		String price = "";
		String ptyname = "";
		String traffic = "";
		String agent="";
		String agenttel="";
		
		
		price = doc.select(".houseInfo .info >ul >li").get(0).text();
		
		ptyname = doc.select(".houseInfo .info >ul >li").get(1).getElementsByClass("floatl").text();
		
		traffic = doc.select(".houseInfo .info >ul >li").get(2).getElementsByClass("floatl").text();
		
		agenttel=doc.getElementById("agtphone").text();
		
		agent = doc.getElementById("Span2").text();
		
		
		ztFangRent.setCreationDate(System.currentTimeMillis());
		ztFangRent.setPrice(price);
		ztFangRent.setPtyname(ptyname);
		ztFangRent.setTraffic(traffic);
		ztFangRent.setAgent(agent);
		ztFangRent.setAgenttel(agenttel);
		ztFangRent.setIsCleaned(false);
		ztFangRent.setModificationDate(System.currentTimeMillis());
		
		System.out.println(ztFangRent.toUpdateSql());
		JDBCTemplate.save(ztFangRent);
		return true;
	}

	@Override
	public int totalPageCount() throws Exception {
		return 100;
	}
	
	public static void main(String[] args) throws Exception {
		
		FangRentSpiderTask task = new FangRentSpiderTask();
		
		SpiderRunParam param = new SpiderRunParam();
		param.setProxy(false);
		
		task.setRunParam(param);
		
		//task.crawlUrl(1);
		task.crawlDetailInfo("http://zu.sz.fang.com/chuzu/1_51293478_-1.htm");
	}

}
