package com.xiaojiang.crawler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;
import com.xiaojiang.crawler.bean.DistrictKejiyuan;
import com.xiaojiang.crawler.query.DistrictKejiyuanQuery;
import com.xiaojiang.util.JDBCTemplate;

public class AnjukeCommunityInfoSpider extends AbstractSpider{
	

	public void crawlUrl(int pageNum) throws Exception {
		
		String url = "http://shenzhen.anjuke.com/community/W0QQp1Z431QQp2Z433QQpZ"+pageNum;
		
		Document doc = this.doGet(url);
		
		Element list = doc.getElementsByClass("pL").get(0).getElementsByClass("list").get(0);
		
		int size = list.getElementsByClass("list_item").size();
		
		for(int i = 0 ; i< size ;i++){
			
			String houseUrl = list.getElementsByClass("list_item").get(i).getElementsByTag("a").attr("href");
			
			System.out.println(houseUrl);
			
			this.saveUrl(houseUrl, 0L);
			
		}
		
	}
	
	public boolean crawlDetailInfo(String url) throws Exception {
		
		WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
		client.getOptions().setJavaScriptEnabled(true);    //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		client.getOptions().setThrowExceptionOnScriptError(false);   
		  
		HtmlPage page = client.getPage(url);
		
		Document doc = Jsoup.parse(page.asXml());
		
		
		Element list = doc.getElementsByClass("comm-list").get(0);
		
		//name 小区名
		String name = list.getElementsByClass("comm-l-detail").get(0).getElementsByTag("dd").get(0).text();
		
		//property_type  物业类型
		String property_type = list.getElementsByClass("comm-l-detail").get(0).getElementsByTag("dd").get(5).text();
		
		//property_fee  物业费
		String property_fee = list.getElementsByClass("comm-l-detail").get(0).getElementsByTag("dd").get(6).text();
		if(!property_fee.contains("暂无")){
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(property_fee);
			if(m.find())
				property_fee = m.group();
		}
		
		
		//total_house 总户数
		String total_house = list.getElementsByClass("comm-r-detail").get(0).getElementsByTag("dd").get(1).text();
		if(!total_house.contains("暂无")){
			Pattern p = Pattern.compile("\\d+");
			Matcher m2 = p.matcher(total_house);
			if(m2.find())
				total_house = m2.group();
		}
		
		//build_age 建筑年代 房龄
		String build_age = list.getElementsByClass("comm-r-detail").get(0).getElementsByTag("dd").get(2).text();
		if(!build_age.contains("暂无")){
			SimpleDateFormat sf  =new SimpleDateFormat("yyyy");
			Date date = sf.parse(build_age);
			String year = sf.format(date);
			int age = 2016-Integer.valueOf(year);
			build_age = String.valueOf(age);
		}
		//capacity 容积率
		String capacity = list.getElementsByClass("comm-r-detail").get(0).getElementsByTag("dd").get(3).text();
		
		//greenary_rate 绿化率
		String greenary_rate = list.getElementsByClass("comm-r-detail").get(0).getElementsByTag("dd").get(6).text();
		if(!greenary_rate.contains("暂无")){
			Pattern p = Pattern.compile("\\d+");
			Matcher m3 = p.matcher(greenary_rate);
			if(m3.find()){
				greenary_rate = m3.group();
				Double green = Double.valueOf(greenary_rate)/100;
				greenary_rate = String.valueOf(green);
			}
		}
		
		
		Element life = doc.getElementById("life_box");
		Pattern p = Pattern.compile("\\d+");
		//交通
		String subway="0";
		String bus="0";
		try {
			String traffic = life.getElementsByClass("traffic").get(0).getElementsByClass("nlist").get(0).text();
			Matcher m4 = p.matcher(traffic);
			List<String> ls = new ArrayList<String>();
			subway = "0";
			bus = "0";
			while(m4.find()){
				ls.add(m4.group());
			}
			if(ls.size()==2){
				subway = ls.get(0);
				bus = ls.get(1);
			}else if(ls.size() == 1){
				bus = ls.get(0);
			}
		} catch (Exception e1) {
			subway="-1";
			bus="-1";
		}
		
		
		//医疗
		int hospital_sum=0;
		try {
			String hospital = life.getElementsByClass("hospital").get(0).getElementsByClass("nlist").get(0).text();
			Matcher m5 = p.matcher(hospital);
			List<String> ls2 = new ArrayList<String>();
			while(m5.find()){
				ls2.add(m5.group());
			}
			hospital_sum = 0;
			if(ls2.size() == 3){
				hospital_sum = Integer.valueOf(ls2.get(0))+Integer.valueOf(ls2.get(1))+Integer.valueOf(ls2.get(2));
			}
		} catch (Exception e) {
			hospital_sum=-1;
		}
		
		//教育
		int school_sum=0;
		try {
			String school = life.getElementsByClass("school").get(0).getElementsByClass("nlist").get(0).text();
			Matcher m6 = p.matcher(school);
			List<String> ls3 = new ArrayList<String>();
			while(m6.find()){
				ls3.add(m6.group());
			}
			school_sum = 0;
			if(ls3.size() == 3){
				school_sum = Integer.valueOf(ls3.get(0))+Integer.valueOf(ls3.get(1))+Integer.valueOf(ls3.get(2));
			}
		} catch (Exception e) {
			school_sum=-1;
		}
		
		//商业
		int commerce_sum=0;
		try {
			String commerce = life.getElementsByClass("commerce").get(0).getElementsByClass("nlist").get(0).text();
			Matcher m7 = p.matcher(commerce);
			List<String> ls4 = new ArrayList<String>();
			while(m7.find()){
				ls4.add(m7.group());
			}
			commerce_sum = 0;
			if(ls4.size() == 3){
				commerce_sum = Integer.valueOf(ls4.get(0))+Integer.valueOf(ls4.get(1))+Integer.valueOf(ls4.get(2));
			}
		} catch (Exception e) {
			commerce_sum=-1;
		}

		
		DistrictKejiyuan kejiyuan = isCrawl(url); 
		if(kejiyuan == null)
			kejiyuan = new DistrictKejiyuan();
		
		kejiyuan.setUrl(url);
		kejiyuan.setCityId(2L);
		kejiyuan.setZoneId(6L);
		kejiyuan.setDistrictId(7L);
		kejiyuan.setName(name);
		kejiyuan.setCapacity(capacity);
		kejiyuan.setGreenaryRate(greenary_rate);
		kejiyuan.setTotalHouse(total_house);
		kejiyuan.setPropertyType(property_type);
		kejiyuan.setPropertyFee(property_fee);
		kejiyuan.setBuildingAge(build_age);
		kejiyuan.setBus(bus);
		kejiyuan.setSubway(subway);
		kejiyuan.setHospital(String.valueOf(hospital_sum));
		kejiyuan.setEducation(String.valueOf(school_sum));
		kejiyuan.setBusiness(String.valueOf(commerce_sum));
		
		System.out.println(kejiyuan.toUpdateSql());
		JDBCTemplate.save(kejiyuan);
		
		return true;
		
	}

	
	private DistrictKejiyuan isCrawl(String url) {
		
		DistrictKejiyuanQuery query = new DistrictKejiyuanQuery();
		query.setUrl(url);
		return (DistrictKejiyuan) JDBCTemplate.findOne(query);
	}

	public int totalPageCount() throws Exception {
		/*
		String url = "http://shenzhen.anjuke.com/community/W0QQpZ1QQp1Z431QQp2Z433";
		
		Document doc = this.doGet(url);
		
		String num = doc.getElementsByClass("PL_1").get(0).getElementsByTag("em").text();
		
		int total =  (int) Math.ceil(Double.valueOf(num)/10);
		
		return total;*/
		return 9;
		
	}

	
	public static void main(String[] args) throws Exception {
		
		SpiderRunParam runParam = new SpiderRunParam();
		runParam.setProxy(false);
		
		AnjukeCommunityInfoSpider spider = new AnjukeCommunityInfoSpider();
		spider.setRunParam(runParam);
		
		//System.out.println(spider.totalPageCount());
		//spider.crawlUrl(1);
		spider.crawlDetailInfo("http://shenzhen.anjuke.com/community/view/727455");
//		SimpleDateFormat sf  =new SimpleDateFormat("yyyy");
//		String s = "2004-08";
//		Date date = sf.parse(s);
//		System.out.println(sf.format(date));

		
		
	}
	
	
	
}
