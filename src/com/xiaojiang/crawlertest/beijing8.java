package com.xiaojiang.crawlertest;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;

public class beijing8 extends AbstractSpider{

	private long date2 = 0L;
	
	@Override
	public boolean crawlDetailInfo(String url) throws Exception {
		
		Document doc = this.doGet(url);
		
		Element clum_title = doc.getElementsByClass("clum_title").get(0);
		
		String date = clum_title.text();
		
		Element zj_area = doc.getElementById("zj_area");
		
		String result = zj_area.text();
		
		
		//判断是否为同一期
		String date1 = date.substring(4,10);
		//System.out.println(date1);
		
		//如果当前date2
		if(Long.parseLong(date1)>date2){
			
			System.out.println(date+" "+result);
			date2=Long.parseLong(date1);
		}
		
		
		
		return true;
		
	}

	//每5秒执行一次
	public static void main(String[] args) throws Exception {
		
		SpiderRunParam runParam = new SpiderRunParam();
		runParam.setProxy(false);
		
		beijing8 task = new beijing8();
		task.setRunParam(runParam);
		
		String url = "http://caipiao.163.com/award/kl8_.html";
		task.crawlDetailInfo(url);
		
	}
	
}
