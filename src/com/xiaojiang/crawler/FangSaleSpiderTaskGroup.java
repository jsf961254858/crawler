package com.xiaojiang.crawler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;
import com.xiaojiang.crawler.bean.ZtFangEsf;
import com.xiaojiang.crawler.query.ZtFangEsfQuery;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.util.JDBCTemplate;


public class FangSaleSpiderTaskGroup extends AbstractSpider{
	
	@Override
	public void group()throws Exception{
		List<String> conditions = new ArrayList<String>();
		String[] cityZoneUrls = {
							
				//南山
				"http://esf.sz.fang.com/house-a087-b0344/",
				"http://esf.sz.fang.com/house-a087-b0346/",
				"http://esf.sz.fang.com/house-a087-b0342/",
				"http://esf.sz.fang.com/house-a087-b0340/",
				"http://esf.sz.fang.com/house-a087-b04800/",
				"http://esf.sz.fang.com/house-a087-b0339/",
				"http://esf.sz.fang.com/house-a087-b0343/",
				"http://esf.sz.fang.com/house-a087-b0345/",
				"http://esf.sz.fang.com/house-a087-b0341/",
											
				};
		//户型id
		for(int i=0;i<cityZoneUrls.length;i++){
			String seedUrl = cityZoneUrls[i]+"i31/";
			conditions.add(seedUrl);
		}
		analysis(conditions);
	}
	
	private void analysis(List<String> conditions) throws DataTaskException{
		for(String url:conditions){
			this.addGroupCondition(url);
		}
	}
	@Override
	public void crawlUrl(int pageNum) throws Exception {
		String url ="http://esf.sz.fang.com/house-a087-b0344/i31/".replace("i31", "i3"+pageNum);
		Document doc = this.doGet(url);
	
		int size = doc.select(".floatr .title >a").size();
		for(int j=0;j<size;j++){
			
			String houseHomePageUrl = "http://esf.sz.fang.com"+doc.select(".floatr .title >a").get(j).attr("href");
			//this.saveUrl(houseHomePageUrl, 0L);
			System.out.println(houseHomePageUrl);
			
		}
		
	}
	
	private static long formatDate(String dateStr) throws DataTaskException{
		
		long curDate = System.currentTimeMillis();

		if(dateStr.contains("秒")){
			
			int seconds = findInteger(dateStr);
			
			return curDate - seconds * 1000;
			
		}else if(dateStr.contains("分钟")){
			
			int minutes = findInteger(dateStr);
			
			return curDate - minutes * 60 * 1000L;
			
		}else if(dateStr.contains("小时")){
			
			int hours = findInteger(dateStr);
			
			return curDate - hours * 60 * 60 * 1000L;
			
		}else if(dateStr.contains("天")){
			
			int day = findInteger(dateStr);
			
			return curDate - day * 24 * 60 * 60 * 1000L;
			
		}else if(dateStr.contains("周")){
			
			int week = findInteger(dateStr);
			
			return curDate - week * 7 * 24 * 60 * 60 * 1000L;
			
		}else if(dateStr.contains("月")){
			
			int month = findInteger(dateStr);
			
			Long m =  (long)month * 30 * 24 * 60 * 60 * 1000L;
			
			return curDate - m;
		}else{
			dateStr = dateStr.replaceAll("[\u4E00-\u9FFF]", "").replaceAll("/n", "").replaceAll("  ", "");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				return sdf.parse(dateStr).getTime();
			} catch (ParseException e) {
				throw new DataTaskException(2, "解析时间出错", e);
			}
		}
	}

	private static Integer findInteger(String s){
		
		String reg = "[0-9]+";
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = p.matcher(s);
		
		String num = "";
		
		while(m.find()){
			num += m.group();
		}
		
		if(num.equals(""))
			return null;
		
		return Integer.parseInt(num);
		
	}
	
	private ZtFangEsf isCrawed(String url){
		ZtFangEsfQuery query = new ZtFangEsfQuery();
		query.setUrl(url);
		return (ZtFangEsf) JDBCTemplate.findOne(query);
	}
	
	
	@Override
	public boolean crawlDetailInfo(String url) throws Exception {
		
		Document doc = this.doGet(url);
		
		ZtFangEsf ztFangEsf = isCrawed(url);
		if(ztFangEsf == null)
			ztFangEsf = new ZtFangEsf();
		
		ztFangEsf.setUrl(url);
		
		
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
		
		ztFangEsf.setPrice(price);
		ztFangEsf.setPtyname(ptyname);
		ztFangEsf.setTraffic(traffic);
		ztFangEsf.setAgent(agent);
		ztFangEsf.setAgenttel(agenttel);
		ztFangEsf.setCreationDate(System.currentTimeMillis());
		ztFangEsf.setModificationDate(System.currentTimeMillis());
		ztFangEsf.setIsCleaned(false);
		
		System.out.println(ztFangEsf.toUpdateSql());
		JDBCTemplate.save(ztFangEsf);
		
		return true;
	}
	
	@Override
	public int totalPageCount() throws Exception {
		Document doc = this.doGet(this.getQueryCondition());
		int count = Integer.parseInt(doc.getElementsByClass("fy_text").get(0).text().split("/")[1]);
		
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		
		SpiderRunParam param = new SpiderRunParam();
		param.setProxy(false);
		
		FangSaleSpiderTaskGroup task = new FangSaleSpiderTaskGroup();
		task.setRunParam(param);
		
		//task.crawlUrl(1);
		
		task.crawlDetailInfo("http://esf.sz.fang.com/chushou/3_174081325.htm");
	}
	
}
