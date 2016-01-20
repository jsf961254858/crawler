package com.xiaojiang.crawler;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;
import com.xiaojiang.crawler.bean.ZtGuotujuBuilding;
import com.xiaojiang.crawler.query.ZtGuotujuBuildingQuery;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.util.JDBCTemplate;


/**
 * 国土局楼栋.
 * @author gehouse
 *
 */
public class GuoTuJuBuildingSpiderTask extends AbstractSpider {
	
	
	String u = "http://ris.szpl.gov.cn/bol/index.aspx";
	
	@Override
	public void crawlUrl(int pageNum) throws Exception {
		Document doc = this.doGet(u);
		String __VIEWSTATE = doc.getElementById("__VIEWSTATE").val();
		String __EVENTVALIDATION = doc.getElementById("__EVENTVALIDATION").val();
		Map<String, String> paramaters = new HashMap<String, String>();
		paramaters.put("AspNetPager1", "go");
		paramaters.put("AspNetPager1_input", pageNum+"");
		paramaters.put("__EVENTARGUMENT", "");
		paramaters.put("__EVENTTARGET", "");
		paramaters.put("__EVENTVALIDATION",__EVENTVALIDATION);
		paramaters.put("__VIEWSTATE", __VIEWSTATE);
		paramaters.put("__VIEWSTATEENCRYPTED", "");
		paramaters.put("__VIEWSTATEGENERATOR", "248CD702");
		paramaters.put("organ_name", "");
		paramaters.put("tep_name", "");
		paramaters.put("site_address", "");
		Document doc1 = this.doPost(u, paramaters);
		
		if(doc1.body().getElementById("DataList1") != null){
			Elements e = doc1.body().getElementById("DataList1").getElementsByTag("tr");
			
			for(int i=3;i<e.size();){
				Elements e2 = e.get(i).getElementsByTag("td");
				String url2 = "http://ris.szpl.gov.cn/bol/"+e2.get(2).getElementsByAttribute("href").get(0).attr("href");

				i = i + 2;
				
				String id = url2.split("\\?")[1].split("=")[1];
				ZtGuotujuBuildingQuery f = new ZtGuotujuBuildingQuery();
				f.setProperty(Long.parseLong(id));
				ZtGuotujuBuilding oz = (ZtGuotujuBuilding) JDBCTemplate.findOne(f);
				if(oz != null){
					continue;
				}
				System.out.println(url2);
				//this.saveUrl(url2, 0L);
			}
		}

	}
	
	private long done(String t) throws Exception{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date dt2 = sdf.parse(t);
		return dt2.getTime() / 1000;
	}
	
	private void parse(String propertyId,Document doc) throws IOException{
		Elements es = doc.body().getElementById("DataList1").getElementsByTag("tr");
		
		for(int i=3;i<es.size();){
			Element e1 = es.get(i);
			String number = e1.getElementsByTag("td").get(1).text().trim();
			
			String id = e1.getElementsByAttribute("href").get(0).attr("href").split("\\?")[1].split("=")[1];
			
			ZtGuotujuBuilding z = new ZtGuotujuBuilding();
			ZtGuotujuBuildingQuery f = new ZtGuotujuBuildingQuery();
			f.setSrcId(id);
			ZtGuotujuBuilding oz = (ZtGuotujuBuilding) JDBCTemplate.findOne(f);
			if(oz != null)
				z.setId(oz.getId());
			
			z.setBuildingNumber(number);
			z.setProperty(Long.parseLong(propertyId));
			z.setSrcId(id);
			
			JDBCTemplate.save(z);
			
			i = i+2;
		}

	}
	
	@Override
	public boolean crawlDetailInfo(String url) throws SQLException, DataTaskException, IOException {
		Document doc = this.doGet(url);
		Elements es = doc.body().getElementsByTag("tr");
		String id = url.split("\\?")[1].split("=")[1];
	
		if(es.get(0).text().contains("项目详细资料")){
			parse(id, doc);
		}
		
		return true;
	}
	
	@Override
	public int totalPageCount() throws Exception {
		/*String url = "http://ris.szpl.gov.cn/bol/index.aspx";
		Document doc = this.doGet(url);
		
		int count = Integer.parseInt(doc.getElementsByClass("PageInfo").get(0).getElementsByTag("b").get(2).text());
		
		return count;*/
		return 1;
	}

	@Override
	public boolean isNewPage(int page, long lastCrawlDate) throws Exception{
		return true;
	}
	
/*	@Override
	public void clean() throws Exception {
		this.cleanQuery = new ZtGuotujuBuildingQuery();
	}*/
	
	public static void main(String[] args) throws Exception {
		
		SpiderRunParam param = new SpiderRunParam();
		param.setProxy(false);
		
		GuoTuJuBuildingSpiderTask task = new GuoTuJuBuildingSpiderTask();
		task.setRunParam(param);
		
		//System.out.println(task.totalPageCount());
		task.crawlUrl(1);
		//System.out.println(task.crawlDetailInfo("http://ris.szpl.gov.cn/bol/projectdetail.aspx?id=24311"));
	}
}
