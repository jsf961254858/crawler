package com.xiaojiang.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.bean.BeanBase;
import com.xiaojiang.crawler.bean.ZtDaikanSale;
import com.xiaojiang.crawler.query.ZtDaikanSaleQuery;
import com.xiaojiang.util.JDBCTemplate;

public class DaikanSaleTest extends AbstractSpider {

	@Override
	public void crawlUrl(int pageNum) throws Exception {

		String url ="http://sz.daikan.cn/sale/search0X0X0X0X0X0X0XXdefXascXdescX0?&p="+pageNum;
		
		Document doc = this.doGet(url);
		int size = doc.select(".hire_in1").size();
		for(int j=0;j<size;j++){
			String houseHomePageUrl = doc.select(".hire_in1").get(j).select(">dl >dt >a").attr("href");
			
			//System.out.println(houseHomePageUrl);

			this.saveUrl(houseHomePageUrl,0l);
		}
	}

	private  ZtDaikanSale isCrawed(String url){
		ZtDaikanSaleQuery query = new ZtDaikanSaleQuery();
		query.setUrl(url);
		return (ZtDaikanSale) JDBCTemplate.findOne(query);
	}
	
	@Override
	public boolean crawlDetailInfo(String url) throws Exception {
		Document doc = this.doGet(url);
		
		ZtDaikanSale ztDaikanSale = isCrawed(url);
		if(ztDaikanSale == null )
			ztDaikanSale = new ZtDaikanSale();
		
		ztDaikanSale.setUrl(url);
		
		String price = "";
		String unitprice = "";
		String layout = "";
		String deco = "";
		String high = "";
		String metro = "";
		String metrostop = "";
		String age = "";
		String direction = "";
		String carsit = "";
		String description = "";
		String agent = "";
		String tel = "";
		String ptyname = "";
		
		ptyname = doc.select(".lpxx_tit1 >h2").text();
		
		Elements elems = doc.select(".place_xx >ul").get(0).children();
		if(elems !=null){
			for(Element elem : elems){
				String text = elem.text();
				if(text.indexOf("出售总价：")!=-1){
					price = text.replaceAll("出售总价：", "");
				}
				if(text.indexOf("出售单价：")!=-1){
					unitprice = text.replaceAll("出售单价：", "");
				}
				if(text.indexOf("房型面积：")!=-1){
					layout = text.replaceAll("房型面积：", "");
				}
				if(text.indexOf("装修情况：")!=-1){
					deco = text.replaceAll("装修情况：", "");
				}
				if(text.indexOf("所在楼层：")!=-1){
					high = text.replaceAll("所在楼层：", "");
				}
				if(text.indexOf("地铁线路：")!=-1){
					metro = text.replaceAll("地铁线路：", "");
				}
				if(text.indexOf("地铁站名：")!=-1){
					metrostop = text.replaceAll("地铁站名：", "");
				}
				if(text.indexOf("建造年代：")!=-1){
					age = text.replaceAll("建造年代：", "");
				}
				if(text.indexOf("朝    向：")!=-1){
					direction = text.replaceAll("朝    向：", "");
				}
				if(text.indexOf("车    位：")!=-1){
					carsit = text.replaceAll("车    位：", "");
				}
			}
		}
		
		description = doc.select(".fyxx_jjrms").text();
		agent = doc.select(".fwdp_xx .list_cent_li >dd >a").text();
		tel = doc.select(".tel_number").get(0).text();
		
		ztDaikanSale.setAge(age);
		ztDaikanSale.setAgent(agent);
		ztDaikanSale.setCarsit(carsit);
		ztDaikanSale.setCreationDate(System.currentTimeMillis());
		ztDaikanSale.setDeco(deco);
		ztDaikanSale.setDescription(description);
		ztDaikanSale.setDirection(direction);
		ztDaikanSale.setHigh(high);
		ztDaikanSale.setLayout(layout);
		ztDaikanSale.setMetro(metro);
		ztDaikanSale.setMetrostop(metrostop);
		ztDaikanSale.setModificationDate(System.currentTimeMillis());
		ztDaikanSale.setPrice(price);
		ztDaikanSale.setPtyname(ptyname);
		ztDaikanSale.setSpiderid(getId());
		ztDaikanSale.setTel(tel);
		ztDaikanSale.setUnitprice(unitprice);
		ztDaikanSale.setUrl(url);
		ztDaikanSale.setIsCleaned(false);
		
		//System.out.println(ztDaikanSale.toUpdateSql());
		
		JDBCTemplate.save(ztDaikanSale);
		
		return true;
	}

	@Override
	public int totalPageCount() throws Exception {
		return 1;
	}

	@Override
	public void clean() throws Exception {
		this.cleanQuery = new ZtDaikanSaleQuery();
		super.clean();
	}

	@Override
	public void saveHouse(BeanBase entity) {
		
		//清洗完房源保存到指定位置
		ZtDaikanSale house = (ZtDaikanSale) entity;
		
		System.out.println(house.getId());
	
	
	}
	
	
	
	
}
