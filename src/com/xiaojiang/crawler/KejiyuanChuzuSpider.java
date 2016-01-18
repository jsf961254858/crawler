package com.xiaojiang.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;
import com.xiaojiang.crawler.bean.DistrictKejiyuan;
import com.xiaojiang.crawler.query.DistrictKejiyuanQuery;
import com.xiaojiang.util.JDBCTemplate;

public class KejiyuanChuzuSpider extends AbstractSpider{

	public void crawlUrl(int pageNum) throws Exception {
		//http://zu.sz.fang.com/house-a087-b0340/a27-i33/
		String url = "http://zu.sz.fang.com/house-a087-b0340/a27-i3"+"pageNum"+"/";
		
		Document doc = this.doGet(url);
		
		int size = doc.select(".floatr .title >a").size();
		
		for(int i=0;i<size;i++){
			
			String houseUrl = "http://zu.sz.fang.com"+doc.select(".floatr .title >a").get(i).attr("href");
			
			//this.saveUrl(houseUrl, 0L);
			System.out.println(houseUrl);
		}
		
	}

	public boolean crawlDetailInfo(String url) throws Exception {
		
		Document doc = this.doGet(url);
		
		//判断是否重复
		
		//int size = doc.select(".houseInfor .info >ul >li").size();
		
		String price = doc.select(".houseInfor .info >ul >li").get(0).getElementsByClass("num").get(0).text();
		System.out.println(price);

		String name = doc.select(".houseInfor .info >ul >li").get(1).getElementsByClass("floatl").get(0).text();
		name=name.split(" ")[1];
		char x = 160;
		String xx = "";
		xx+=x;
		String names[] = name.split(xx);
		String community = names[0];
		System.out.println(community);
		
		DistrictKejiyuanQuery query = new DistrictKejiyuanQuery();
		query.setName(community);
		DistrictKejiyuan kejiyuan = (DistrictKejiyuan) JDBCTemplate.findOne(query);
		
		System.out.println(kejiyuan.getId());
		
		String house = doc.select(".houseInfor .info >ul >li").get(3).getElementsByClass("floatl").get(0).text();
		System.out.println(house);
		
		String[] hou = house.split("-");
		String house_type = hou[0].trim().split("：")[1];
		System.out.println(house_type);
		
		List<String> ls = new ArrayList<String>();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(hou[1].trim());
		while(m.find()){
			ls.add(m.group());
		}
		String room="0";
		String hall="0";
		if(ls.size() >= 2){
			room = ls.get(0);
			hall = ls.get(1);
		}else if(ls.size() == 1){
			room = ls.get(0);
		}
		System.out.println(ls.get(0)+"   "+ls.get(1));
		
		String area = "0";
		Matcher m2 = p.matcher(hou[2].trim());
		if(m2.find())
			area = m2.group();
		System.out.println(area);
		
		String orientation = hou[3].trim();
		switch(orientation){
			case "西北" : orientation = "1"; break;
			case "西" : orientation = "2"; break;
			case "北" : orientation = "3"; break;
			case "东北" : orientation = "4"; break;
			case "东" : orientation = "5"; break;
			case "西南" : orientation = "6"; break;
			case "南" : orientation = "7"; break;
			case "东南" : orientation = "8"; break;
			case "南北" : orientation = "9"; break;
		}
		
		System.out.println(orientation);
		
		String[] hh = hou[4].trim().split("/");
		
		String floor = hh[0];
		//switch(floor)
		
		
		System.out.println(hh[0] + "   " + hh[1]);
		
		
		
		String allocation = doc.select(".houseInfor .info >ul >li").get(4).text();
		if(allocation.contains("暂无")){
			allocation = "0";
		}else{
			
		}
		System.out.println(allocation);
		
		
		return true;
		
	}

	public int totalPageCount() throws Exception {
		return 48;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		SpiderRunParam runParam = new SpiderRunParam();
		runParam.setProxy(false);
		
		KejiyuanChuzuSpider spider = new KejiyuanChuzuSpider();
		spider.setRunParam(runParam);
		
		//spider.crawlUrl(1);
		
		spider.crawlDetailInfo("http://zu.sz.fang.com/chuzu/14_364034_-1.htm");
		
	}
	

}
