package com.xiaojiang.crawler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.crawler.bean.ZtAnjukeSale;
import com.xiaojiang.crawler.query.ZtAnjukeSaleQuery;
import com.xiaojiang.httpclient.HttpClientRequestHandler;
import com.xiaojiang.util.JDBCTemplate;


public class AnjukeSaleSpiderTask extends AbstractSpider {
	private static Logger logger = Logger.getLogger(AnjukeSaleSpiderTask.class);
	
	@Override
	public void crawlUrl(int pageNum) throws Exception {
		String url = "http://shenzhen.anjuke.com/sale/o5-p"+pageNum+"/#filtersort";
	
//		Document doc = this.doGet(url);
		Document doc = HttpClientRequestHandler.doGet(url, null, null);
		if(doc == null)
			return;
		
		int size = doc.select(".house-title").size();
		for (int j=0;j<size; j++){
			
			String houseHomePageUrl = doc.select(".house-title").get(j).select(">a").attr("href");
//			Document timeDoc = this.doGet(houseHomePageUrl);
			Document timeDoc = HttpClientRequestHandler.doGet(houseHomePageUrl, null, null);
			
			String date = timeDoc.select(".extra-info").text();
			long updateDate = formatDate(date);
			this.saveUrl(houseHomePageUrl, updateDate);
			
			System.out.println(houseHomePageUrl);
			System.out.println(updateDate);
			
		}
	}
	
	//解析发布日期
	private Long formatDate(String t){
		Pattern p = Pattern.compile("\\d{4}(.)\\d{2}(.)\\d{2}(.)");
		Matcher m = p.matcher(t);
		
		if(m.find()){
	
			String str = m.group();
			str = str.replace("年", "-").replace("月", "-").replace("日", "");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(str);
				return date.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return System.currentTimeMillis();
	}
	
	private ZtAnjukeSale isCrawed(String url){
		ZtAnjukeSaleQuery query = new ZtAnjukeSaleQuery();
		query.setUrl(url);
		return (ZtAnjukeSale) JDBCTemplate.findOne(query);
	}
	
	
	@Override
	public boolean crawlDetailInfo(String url) throws Exception {

		Document doc = this.doGet(url);
	
		if(doc == null)
			return false;
		
		ZtAnjukeSale ztAnjukeSale = isCrawed(url);
		if(ztAnjukeSale == null)
			ztAnjukeSale = new ZtAnjukeSale();
		
		ztAnjukeSale.setUrl(url);
		String price = "";
		String ptyname = "";
		String layout = "";
		String inch = "";
		String direction = "";
		String unitprice = "";
		String high = "";
		String type = "";
		String deco = "";
		String description = "";
		String agent  = "";
		String tel = "";
		
		Elements elems = doc.select(".pinfo #prop_infor .phraseobox .litem").get(0).children();
		logger.info(elems.text());
		if(elems!=null){
			for(Element elem:elems){
				String text = elem.text();
				if(text.indexOf("售价")!=-1){
					price = text.replaceAll("售价", "").replaceAll("/n", "");
				}
				if(text.indexOf("单价")!=-1){
					unitprice = text.replaceAll("单价", "").replaceAll("/n", "");
				}
				if(text.indexOf("物业类型")!=-1){
					type = text.replaceAll("物业类型", "").replaceAll("/n", "");
				}
			}
		}
		
		Elements elems2 = doc.select(".pinfo #prop_infor .phraseobox .ritem").get(0).children();
		logger.info(elems2.text());
		if(elems2!=null){
			for(Element elem:elems2){
				String text = elem.text();
				if(text.indexOf("房型")!=-1){
					layout = text.replaceAll("房型", "").replaceAll("/n", "");
				}
				if(text.indexOf("面积")!=-1){
					inch = text.replaceAll("面积", "").replaceAll("/n", "");
				}
				if(text.indexOf("朝向")!=-1){
					direction = text.replaceAll("朝向", "").replaceAll("/n", "");
				}
				if(text.indexOf("楼层")!=-1){
					high = text.replaceAll("楼层", "").replaceAll("/n", "");
				}
				if(text.indexOf("装修")!=-1){
					deco = text.replaceAll("装修", "").replaceAll("/n", "");
				}
			}
		}
		
		try{
			description = doc.select(".pro_detail #propContent").text().replaceAll("/n", "");
		}catch(Exception e){
			description = "";
		}
		try{
			agent = doc.select("#broker_true_name").text().replaceAll("/n", "");
		}catch(Exception e){
			agent = "";
		}
		try{
			tel = doc.select(".broker_tel").text();
		}catch(Exception e){
			tel = "";
		}
		try{
			ptyname = doc.select(".pinfo .hd >h5").get(0).text();
		}catch(Exception e){
			ptyname = "";
		}
		
		ztAnjukeSale.setAgent(agent);
		ztAnjukeSale.setCreationDate(System.currentTimeMillis());
		ztAnjukeSale.setDeco(deco);
		ztAnjukeSale.setDescription(description);
		ztAnjukeSale.setDirection(direction);
		ztAnjukeSale.setHigh(high);
		ztAnjukeSale.setInch(inch);
		ztAnjukeSale.setLayout(layout);
		ztAnjukeSale.setModificationDate(System.currentTimeMillis());
		ztAnjukeSale.setPrice(price);
		ztAnjukeSale.setPtyname(ptyname);
		ztAnjukeSale.setSpiderid(getId());
		ztAnjukeSale.setTel(tel);
		ztAnjukeSale.setType(type);
		ztAnjukeSale.setUnitprice(unitprice);
		ztAnjukeSale.setIsCleaned(false);
		
		JDBCTemplate.save(ztAnjukeSale);
		System.out.println(ztAnjukeSale.toUpdateSql());
		return true;
	}
	
	
	@Override
	public int totalPageCount() throws Exception {
		
		return 1;
	}
	
	@Override
	public boolean isNewPage(int page, long lastCrawlDate) throws Exception{
		String url = "http://shenzhen.anjuke.com/sale/o5-p"+page+"/#filtersort";
		Document doc = this.doGet(url);
		String houseHomePageUrl = doc.select(".house-title").last().select(">a").attr("href");
		
		Document timeDoc = this.doGet(houseHomePageUrl);
		String date = timeDoc.select(".extra-info").text();
		long updateDate = formatDate(date);
		
		if(updateDate >= lastCrawlDate){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		
		AnjukeSaleSpiderTask task = new AnjukeSaleSpiderTask();
		
		task.crawlUrl(1);
		//System.out.println(task.totalPageCount());
		
	}
}
