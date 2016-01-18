package com.xiaojiang.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;
import com.xiaojiang.crawler.bean.Characteristic;
import com.xiaojiang.crawler.bean.CityCommunity;
import com.xiaojiang.crawler.bean.CityDistrict;
import com.xiaojiang.crawler.query.CharacteristicQuery;
import com.xiaojiang.crawler.query.CityCommunityQuery;
import com.xiaojiang.crawler.query.CityDistrictQuery;
import com.xiaojiang.httpclient.HttpClientRequestHandler;
import com.xiaojiang.util.JDBCTemplate;

public class CharacteristicSpider extends AbstractSpider{
	
	

	@Override
	public void crawlUrl(int pageNum) throws Exception {
		
		String url="http://zu.sz.fang.com/house/i3"+pageNum+"/";
		
		System.out.println(url);
		
		Document doc = this.doGet(url);
		
		int size = doc.select(".floatr .title >a").size();
		
		System.out.println(size);
		
		for(int i=0;i<size;i++){
			
			String houseUrl = "http://zu.sz.fang.com"+doc.select(".floatr .title >a").get(i).attr("href");
			
			this.saveUrl(houseUrl, 0L);
			System.out.println(houseUrl);
		}
		
	}

	@Override
	public boolean crawlDetailInfo(String url) throws Exception {
		
		Document doc = this.doGet(url);
		
		Characteristic character = isCrawled(url);
		if(character == null)
			character = new Characteristic();
		
		//出租情况 合租 忽略
		if(url.contains("hezu"))
			return false;
		
		//户型   2室1厅1卫   处理：厅室
		String huxing = doc.select(".Huxing >li").get(3).select(".info").text();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(huxing);
		List<String> ls = new ArrayList<String>();
		String room = "0";//室
		String hall = "0";//厅
		while(m.find()){
			if(!m.group().equals("")){
				ls.add(m.group());
			}
		}
		if(ls.size() >= 2){
			room = ls.get(0);
			hall = ls.get(1);
		} else if(ls.size() ==1){
			room = ls.get(0);
		}
		
		//交通  处理：公交数和地铁数
		String traffic = "";
		try {
			traffic = doc.select(".Huxing >li").get(9).select(".info").get(0).
					getElementsByTag("span").attr("title");
		} catch (Exception e) {

		}
		//  地铁数
		Pattern p2 = Pattern.compile("([0-9])号");
		Matcher m2 = p2.matcher(traffic);
		int subway = 0;
		while(m2.find()){
			subway++;
		}
		
		// 公交数
		Pattern p22 = Pattern.compile("\\d+");
		Matcher m22 = p22.matcher(traffic);
		int bus1 = 0;
		while(m22.find()){
			bus1++;
		}
		int bus = bus1-subway;
		
		

		//房屋概况    处理：面积、朝向、楼层、装修
		//面积
		String area = "";
		area = doc.select(".Huxing >li").get(5).select(".info").text();
		//System.out.println(area);
		Pattern p31 = Pattern.compile("\\d+");
		Matcher m31 = p31.matcher(area);
		if(m31.find())
			area = m31.group();
		
		//朝向   西北=1，西=2，北=3，东北=4，东=5，西南=6，南=7，东南=8
		String orientation = "";
		orientation = doc.select(".Huxing >li").get(6).select(".info").text();
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
		
		//楼层
		String floor = "";
		floor = doc.select(".Huxing >li").get(7).select(".info").text();
		Pattern p32 = Pattern.compile("\\d+");
		Matcher m32 = p32.matcher(floor);
		if(m32.find())
			floor = m32.group();
		
		//装修   毛坯=1，简单装修=2，中等装修=3，精装修=4，豪华装修=5
		String decoration="";
		decoration = doc.select(".Huxing >li").get(8).select(".info").text();
		switch(decoration){
			case "毛坯" : decoration = "1"; break;
			case "简单装修" : decoration = "2"; break;
			case "简装修" : decoration = "2"; break;
			case "中等装修" : decoration = "3"; break;
			case "中装修" : decoration = "3"; break;
			case "精装修" : decoration = "4"; break;
			case "豪华装修" : decoration = "5"; break;
			case "豪装修" : decoration = "5"; break;
		}
		
		//价格
		String price = "";
		price = doc.select(".houseInfo .info >ul >li").get(0).select(".num").text();
		
		
		//小区名    处理：城区、片区、小区
		String name = "";
		name = doc.select(".houseInfo .info >ul >li").get(1).select(".floatl").text();
		name=name.split(" ")[1];
		char x = 160;
		String xx = "";
		xx+=x;
		String names[] = name.split(xx);
		String community = names[0];
		String zone = names[2];
		String district = names[3];
		
		Long community_id = 0L;
		Long district_id = 0L;
		int zone_id = 0;
		
		
		CityCommunityQuery query = new CityCommunityQuery();
		query.setCommunityName(community);
		CityCommunity cityCommunity = (CityCommunity) JDBCTemplate.findOne(query);
		if(cityCommunity == null ){
			cityCommunity = new CityCommunity();
			
			CityDistrictQuery query1 = new CityDistrictQuery();
			query1.setDistrictName(district);
			CityDistrict cityDistrict = (CityDistrict) JDBCTemplate.findOne(query1);
			
			cityCommunity.setCityId(2);
			cityCommunity.setZoneId(cityDistrict.getZoneId());
			cityCommunity.setDistrictId(cityDistrict.getId());
			cityCommunity.setCommunityName(community);
			
			JDBCTemplate.save(cityCommunity);
			
			cityCommunity = (CityCommunity) JDBCTemplate.findOne(query);
		} 
		community_id = cityCommunity.getId();
		district_id = cityCommunity.getDistrictId();
		zone_id = cityCommunity.getZoneId();

		
		//房屋配置 处理：家电和设施等 每项计1分
		String house_allocation1 = "";
		try {
			house_allocation1 = doc.select(".houseInfo .info >ul >li").get(4).text();
		} catch (Exception e) {
			
		}
		Pattern p41 = Pattern.compile(",");
		Matcher m41 = p41.matcher(house_allocation1);
		int allocation1=0;
		while(m41.find())
			allocation1++;
		String house_allocation2 = "";
		try {
			house_allocation2 = doc.select(".houseInfo .info >ul >li").get(5).text();
		} catch (Exception e) {
			
		}
		Matcher mm = p41.matcher(house_allocation2);
		int allocation2=0;
		while(mm.find())
			allocation2++;
		if(allocation1 != 0)
			allocation1++;
		if(allocation2 != 0)
			allocation2++;
		int allocation = allocation1 + allocation2;
		
		
		character.setUrl(url);
		
		character.setRoom(room); //几室
		character.setHall(hall); //几厅
		character.setSubway(String.valueOf(subway)); //地铁
		character.setBus(String.valueOf(bus)); //公交
		character.setArea(area); //面积
		character.setOrientation(orientation); //朝向
		character.setFloor(floor); //楼层
		character.setDecoration(decoration); //装修情况
		character.setPrice(price);  //房价
		character.setHouseAllocation(String.valueOf(allocation)); //房屋配置
		character.setCommunity(String.valueOf(community_id)); //小区id
		character.setDistrict(String.valueOf(district_id)); //片区id
		character.setZone(String.valueOf(zone_id));  //城区id
		
		System.out.println(character.toUpdateSql());
		JDBCTemplate.save(character);
		
		return true;
		
	}

	private Characteristic isCrawled(String url) {
		
		CharacteristicQuery query = new CharacteristicQuery();
		query.setUrl(url);
		
		return (Characteristic) JDBCTemplate.findOne(query);
	}

	@Override
	public int totalPageCount() throws Exception {
		
		return 100;
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		CharacteristicSpider spider = new CharacteristicSpider();
		
		SpiderRunParam runParam = new SpiderRunParam();
		runParam.setProxy(false);
		
		spider.setRunParam(runParam);
		
		//spider.crawlUrl(1);
		
		spider.crawlDetailInfo("http://zu.sz.fang.com/chuzu/3_176658525_1.htm");
		/*
		String url="http://zu.sz.fang.com/house/i3100/";
		
		String proxyIp = "119.188.94.145";
		Integer proxyPort = 80;
		
		Document doc = HttpClientRequestHandler.doGet(url, proxyIp, proxyPort);
		
		int size = doc.select(".floatr .title >a").size();
		
		System.out.println(size);*/
		
	}

	
}
