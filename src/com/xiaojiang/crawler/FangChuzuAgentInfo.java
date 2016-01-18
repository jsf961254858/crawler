package com.xiaojiang.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;
import com.xiaojiang.crawler.bean.ZtFangChuzuAgentinfo;
import com.xiaojiang.crawler.bean.ZtFangRent;
import com.xiaojiang.crawler.query.Zt58ChuzuAgentinfoQuery;
import com.xiaojiang.crawler.query.ZtFangRentQuery;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.util.JDBCTemplate;



public class FangChuzuAgentInfo extends AbstractSpider {
	
	@Override
	public void group() throws Exception{
		List<String> conditions = new ArrayList<String>();
		String[] cityZoneUrls = {
				//南山
				"http://zu.sz.fang.com/house-a087-b0344/"/*,
				"http://zu.sz.fang.com/house-a087-b0346/",
				"http://zu.sz.fang.com/house-a087-b0342/",
				"http://zu.sz.fang.com/house-a087-b0340/",
				"http://zu.sz.fang.com/house-a087-b04800/",
				"http://zu.sz.fang.com/house-a087-b0339/",
				"http://zu.sz.fang.com/house-a087-b0343/",
				"http://zu.sz.fang.com/house-a087-b0345/",
				"http://zu.sz.fang.com/house-a087-b0341/"*/
				
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
		String url = this.getQueryCondition().replace("i31", "i3"+pageNum);
		//String url ="http://zu.sz.fang.com/house-a087-b0344/i31/".replace("i31", "i3"+pageNum);
		Document doc = this.doGet(url);
	
		int size = doc.select(".floatr .title >a").size();
		for(int j=0;j<size;j++){
			
			String houseHomePageUrl = "http://zu.sz.fang.com"+doc.select(".floatr .title >a").get(j).attr("href");
			this.saveUrl(houseHomePageUrl, 0L);
			System.out.println(houseHomePageUrl);
			
		}
	}
	
	private ZtFangChuzuAgentinfo isCrawed(String url){
		Zt58ChuzuAgentinfoQuery query = new Zt58ChuzuAgentinfoQuery();
		query.setUrl(url);
		
		return (ZtFangChuzuAgentinfo) JDBCTemplate.findOne(query);
	}
	
	@Override
	public boolean crawlDetailInfo(String url) throws Exception {

		Document doc = this.doGet(url);

		ZtFangChuzuAgentinfo entity = isCrawed(url);
		if(entity == null)
			entity = new ZtFangChuzuAgentinfo();
		
		entity.setUrl(url);
		
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
		
		
		entity.setCreationDate(System.currentTimeMillis());
		entity.setPrice(price);
		entity.setPtyname(ptyname);
		entity.setTraffic(traffic);
		entity.setAgent(agent);
		entity.setAgenttel(agenttel);
		entity.setIsCleaned(false);
		entity.setModificationDate(System.currentTimeMillis());
		
		//System.out.println(ztFangRent.toUpdateSql());
		JDBCTemplate.save(entity);
		return true;
	}

	
	@Override
	public int totalPageCount() throws Exception {
		return 1;
	}
	
	public static void main(String[] args) throws Exception {
		
		SpiderRunParam param = new SpiderRunParam();
		param.setProxy(false);
		
		FangChuzuAgentInfo task = new FangChuzuAgentInfo();
		task.setRunParam(param);
		
		//task.crawlUrl(1);
		
		System.out.println(task.crawlDetailInfo("http://zu.sz.fang.com/chuzu/1_51236875_-1.htm"));
		
	}
	
	
}
