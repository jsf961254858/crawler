package com.xiaojiang.crawler;


import java.sql.SQLException;
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
import com.xiaojiang.crawler.bean.ZtEejuSale;
import com.xiaojiang.crawler.query.ZtEejuSaleQuery;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.util.JDBCTemplate;



/**
 * 易居
 * 
 * @author gehouse
 * 
 */
public class EejuSaleSpiderTask extends AbstractSpider {
	private static Logger logger = Logger.getLogger(EejuSaleSpiderTask.class);

	@Override
	public void crawlUrl(int pageNum) throws Exception{
		String url = "http://sz.eeju.com/esf/salei" +pageNum+"/";
		Document doc = this.doGet(url);
	
		for (int j = 0; j < 15; j++) {
			String url1 = null;
			try {
				url1 = doc.getElementById("list_box").getElementsByTag("li").get(j).getElementsByTag("a").get(0).attr("href");
				long updateDate = done(url1);
				
				this.saveUrl(url1, updateDate);
			} catch (IndexOutOfBoundsException e) {
				continue;
			} catch (NullPointerException e) {
				continue;
			}
		}
	}
	
	private long done(String url) throws Exception{
		Document docInfo = this.doGet(url);
	
		String t = docInfo.getElementsByClass("time").get(0).getElementsByTag("i").get(0).text();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date dt2 = sdf.parse(t);
		return dt2.getTime() / 1000;
	}

	@Override
	public boolean crawlDetailInfo(String url) throws SQLException, DataTaskException {

		Document docInfo = this.doGet(url);

		String broker = null; // 经纪人姓名
		String tel = null;// 经纪人电话
		String district_name = null;// 小区名
		String all_price = null;// 总价
		String per_price = null;// 单价

		String room_type = null; // 户型
		String room_inch = null;// 房屋面积
		String room_direct = null;// 房屋朝向
		String room_high = null;// 楼层
		String deco = null;// 装修类型

		String desc = null;// 房屋描述
		String addr = null; // 小区地址

		String building_age = null;//建筑年代
		String src_id = null;//原房源id
		Long lTime = null;
		
		try {
			broker = docInfo.getElementsByClass("broker_name").get(0).text().toString().replace("/n", "");
			
		} catch (IndexOutOfBoundsException e1) {
			broker = "";
		} catch (NullPointerException e2) {
			broker = "";
		}

		try {
			tel = docInfo.getElementsByClass("broker_links").get(0).getElementsByTag("li").get(3).text().split("手机：")[1];

		} catch (ArrayIndexOutOfBoundsException e3) {
			tel = "";
		} catch (IndexOutOfBoundsException e1) {
			tel = "";
		} catch (NullPointerException e2) {
			tel = "";
		}
		
		Elements es = docInfo.getElementsByClass("house_info").get(0).getElementsByTag("li");
		
		Elements es2 = docInfo.getElementsByClass("house_info2").get(0).getElementsByTag("li");
		
		try {
			all_price = es.get(0).getElementsByTag("em").text(); // 万
			
		} catch (NullPointerException e2) {
			all_price = "";
		}
		try {
			per_price = es.get(2).text().split("单价：")[1];
			
		} catch (ArrayIndexOutOfBoundsException e3) {
			per_price = "";
		} catch (IndexOutOfBoundsException e1) {
			per_price = "";
		} catch (NullPointerException e2) {
			per_price = "";
		}
		
		try {
			room_inch = es2.get(2).text().split("建筑面积：")[1];

		} catch (ArrayIndexOutOfBoundsException e3) {
			room_inch = "";
		} catch (IndexOutOfBoundsException e1) {
			room_inch = "";
		} catch (NullPointerException e2) {
			room_inch = "";
		}
		try {
			building_age = es2.get(6).text().split("年代：")[1];

		} catch (ArrayIndexOutOfBoundsException e3) {
			building_age = "";
		} catch (IndexOutOfBoundsException e1) {
			building_age = "";
		} catch (NullPointerException e2) {
			building_age = "";
		}
		
		try {
			room_type = es2.get(1).text().split("房型：")[1];

		} catch (ArrayIndexOutOfBoundsException e3) {
			room_type = "";
		} catch (IndexOutOfBoundsException e1) {
			room_type = "";
		} catch (NullPointerException e2) {
			room_type = "";
		}
		try {
			room_direct = es2.get(4).text().split("朝向：")[1];

		} catch (ArrayIndexOutOfBoundsException e3) {
			room_direct = "";
		} catch (IndexOutOfBoundsException e1) {
			room_direct = "";
		} catch (NullPointerException e2) {
			room_direct = "";
		}
		try {
			room_high = es2.get(5).text().split("楼层：")[1];

		} catch (ArrayIndexOutOfBoundsException e3) {
			room_high = "";
		} catch (IndexOutOfBoundsException e1) {
			room_high = "";
		} catch (NullPointerException e2) {
			room_high = "";
		}
		
		try {
			deco = es2.get(3).text().split("装修：")[1];
	
		} catch (ArrayIndexOutOfBoundsException e3) {
			deco = "";
		} catch (IndexOutOfBoundsException e1) {
			deco = "";
		} catch (NullPointerException e2) {
			deco = "";
		}
		
		try {
			district_name = es2.get(0).text().split("物业名称：")[1].trim();
			
		} catch (ArrayIndexOutOfBoundsException e3) {
			district_name = "";
		} catch (IndexOutOfBoundsException e1) {
			district_name = "";
		} catch (NullPointerException e2) {
			district_name = "";
		}
		try {
			addr = es2.get(7).text().split(" ")[3];
			
		} catch (ArrayIndexOutOfBoundsException e3) {
			addr = "";
		} catch (IndexOutOfBoundsException e1) {
			addr = "";
		} catch (NullPointerException e2) {
			addr = "";
		}
		try {
			desc = docInfo.getElementsByClass("summarize").get(0).getElementsByTag("p").get(3).text();

		} catch (ArrayIndexOutOfBoundsException e3) {
			desc = "";
		} catch (IndexOutOfBoundsException e1) {
			desc = "";
		} catch (NullPointerException e2) {
			desc = "";
		}
		
		try {
			src_id = url.split("\\/")[4].split("\\.")[0];

		} catch (ArrayIndexOutOfBoundsException e3) {
			src_id = "";
		} catch (IndexOutOfBoundsException e1) {
			src_id = "";
		} catch (NullPointerException e2) {
			src_id = "";
		}
		
		String t = docInfo.getElementsByClass("time").get(0).getElementsByTag("i").get(0).text();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date dt2;
		try {
			dt2 = sdf.parse(t);
			lTime = dt2.getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ZtEejuSale z = new ZtEejuSale();
		z.setAddr(addr);
		z.setAge(building_age);
		z.setDeco(deco);
		z.setDirection(room_direct);
		z.setHigh(room_high);
		z.setInch(room_inch);
		z.setInfo(desc);
		z.setPrice(all_price);
		z.setPerprice(per_price);
		z.setPtyname(district_name);
		z.setRoomtype(room_type);
		z.setUrl(url);
		z.setSrcid(src_id);
		z.setCreationDate(System.currentTimeMillis());
		z.setModificationDate(System.currentTimeMillis());
		z.setIsCleaned(false);
		
		ZtEejuSaleQuery f = new ZtEejuSaleQuery();
		f.setSrcid(src_id);
		ZtEejuSale oz = (ZtEejuSale) JDBCTemplate.findOne(f);
		if(oz != null)
			z.setId(oz.getId());
		
		JDBCTemplate.save(z);
		
		return true;
	}

	@Override
	public int totalPageCount() throws Exception {
		Document doc_count = this.doGet("http://sz.eeju.com/esf/");
		

		int count = Integer.parseInt(doc_count.getElementsByClass("page-info").get(0).text().split("\\/")[1]);

		// count =10;

		return count;
	}

	@Override
	public boolean isNewPage(int page, long lastCrawlDate) throws Exception{
	
		String url = "http://sz.eeju.com/esf/salei" +page+"/";
		
		Document doc = this.doGet(url);
		
		Element list = doc.getElementById("list_box");
		
		Element lastHouse = list.getElementsByTag("li").last();
		
		String url1 = lastHouse.getElementsByTag("a").get(0).attr("href");
		
		long updateDate = done(url1);
		
		if(updateDate >= lastCrawlDate){
			return true;
		}
	
		return false;
	}

	public static void main(String[] args) throws SQLException {
		
	}
}
