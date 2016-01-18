package com.xiaojiang.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;
import com.xiaojiang.crawler.bean.Zt58PersonChuzu;
import com.xiaojiang.crawler.query.Zt58PersonChuzuQuery;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.util.JDBCTemplate;

public class Five8PersonChuzuSpider extends AbstractSpider{

	//private static Logger logger = Logger.getLogger(Five8PersonChuzu.class);
	
	private static final String DOMAIN = "http://sz.58.com/chuzu/0/";
	
	private static final int maxPageNum = 70;
	
	
	@Override
	public void crawlUrl(int pageNum) throws Exception {

		String url = DOMAIN+ "pn"+pageNum;
		
		Document doc = this.doGet(url);
		
		String title = doc.getElementsByTag("title").get(0).text();
		
		if(title.contains("验证码")){
			//logger.error("抓取58同城出租房需要验证码校验");
			throw new DataTaskException(202, "爬虫已被发现", null);
		}
		
		Element infolist = doc.getElementById("infolist");
		
		Element houselist = infolist.getElementsByClass("tbimg").get(0);
		
		Elements trs = houselist.getElementsByTag("tr");
		
		for(int j = 0; j<trs.size();j++){
			
			Element house = trs.get(j);
			
			String houseDetailUrl = house.getElementsByTag("h1").get(0).getElementsByTag("a").attr("href");
			
			System.out.println(houseDetailUrl);
			
			this.saveUrl(houseDetailUrl, 0L);
		}

	}
	
	@Override
	public boolean crawlDetailInfo(String url) throws Exception {
		
		Document doc = this.doGet(url);
		
		String title = doc.getElementsByTag("title").get(0).text();
		
		if(title.contains("验证码")){
			//logger.error("抓取58同城出租房需要验证码校验");
			throw new DataTaskException(202, "爬虫已被发现", null);
		}
		
		Zt58PersonChuzu entity = new Zt58PersonChuzu();
		//获取url
		entity.setUrl(url);
		
		//获取title
		Elements bigtitle = doc.getElementsByClass("bigtitle");
		if(bigtitle != null && bigtitle.size() > 0){
			String houseTitle = bigtitle.get(0).text().replaceAll("/n", "");
			entity.setTitle(houseTitle);
		}
		
		Element suUl = doc.getElementsByClass("suUl").get(0);
		
		Elements lis2 = suUl.getElementsByTag("li");
		
		for(Element li : lis2){
			
			Elements cols = li.getElementsByClass("su_tit");
			if(cols == null || cols.size() == 0)
				continue;
			
			Elements values = li.getElementsByClass("su_con");
		
			String col = cols.get(0).text().trim();
			
			if(col.equals("价格")){
				
				String price = values.get(0).getElementsByClass("bigpri").get(0).text();
				//获取价格
				entity.setTotalPrice(findDouble(price));
				String payway = values.get(0).getElementsByClass("f12").get(0).text().trim();
				entity.setPayway(payway.replaceAll("\n", "").replaceAll("/n", "").trim());
			
			}else if(col.equals("概况")){
				
				String value = values.get(0).text().trim();
				
				entity.setBedroom(findBedrooms(value));
				entity.setLivingroom(findLivingrooms(value));
				entity.setWashroom(findWashrooms(value));
				entity.setKitchenroom(findKitchenrooms(value));
				entity.setArea(findAreas(value));
				entity.setHouseType(findHouseType(value));
				entity.setDecoration(findDecoration(value));
				entity.setDirection(findDirection(value));
				
			}else if(col.equals("整体")){
				
				String value = values.get(0).text().trim();
				
				entity.setBedroom(findBedrooms(value));
				entity.setLivingroom(findLivingrooms(value));
				entity.setWashroom(findWashrooms(value));
				entity.setKitchenroom(findKitchenrooms(value));
				entity.setDecoration(findDecoration(value));
			
			}else if(col.equals("出租")){
				String value = values.get(0).text().trim();
				
				entity.setArea(findAreas(value));
				entity.setHouseType(findHouseType(value));
				entity.setDirection(findDirection(value));
				
			}else if(col.equals("区域")){
				
				String value = "";
				
				if(values == null|| values.size() == 0){
					
					Element  newLi = li.removeClass("f12");
					value = newLi.text();
					value = value.substring(2, value.length());
					
					String[] vs = value.split("-");
					
					if(entity.getZoneName() == null){
						entity.setZoneName(vs[0].trim());
					}
					if(entity.getDisName() == null){
						entity.setDisName(vs[1].trim());
					}
					
					String ptyName = vs[2].trim().replaceAll("找附近工作", "");
					
					if(ptyName.contains("（")){
						ptyName = ptyName.substring(0, ptyName.indexOf("（"));
					}
					
					entity.setPtyName(ptyName.trim());
					
				}else{
					
					value = values.get(0).text().trim();
					
					String[] vs = value.split("-");
					
					if(entity.getZoneName() == null){
						entity.setZoneName(vs[0].trim());
					}
					if(entity.getDisName() == null){
						entity.setDisName(vs[1].trim());
					}
					
					String ptyName = vs[2].trim().replaceAll("找附近工作", "");
					
					if(ptyName.contains("（")){
						ptyName = ptyName.substring(0, ptyName.indexOf("（"));
					}
					
					entity.setPtyName(ptyName.trim());
				}
				
			}else if(col.equals("地址")){
				
				String value = values.get(0).text().trim();
				entity.setAddress(value.replaceAll("\n", "").replaceAll("/n", ""));
			
			}else if(col.equals("入住")){
				
				String value = values.get(0).text().trim();
				entity.setLivetime(value);
				
			}else if(col.equals("配置")){
				
				String value = values.get(0).text().trim();
				entity.setSupport(value);
				
			}
		}
		
		//获取图片url
		String picture="";
		
		Element  yzt= doc.getElementsByClass("yzf-zf-tit").get(0);
		if(yzt.getElementsByClass("g_img").size() > 0){
			String g_img = yzt.getElementsByClass("g_img").get(0).getElementsByTag("img").attr("src");
			//System.out.println(g_img);
			picture += (g_img+",");
			Element g_thumb_main = yzt.getElementsByClass("g_thumb_main").get(0);
			
			Elements lis = g_thumb_main.getElementsByTag("li");
			
			for(int i=0 ; i<lis.size();i++){
				
				String img = lis.get(i).getElementsByTag("li").get(0).getElementsByTag("img").attr("src");
				img= img.replace("tiny", "big");
				picture += (img+",");
			}
		}
		entity.setPictureinfo(picture);
		
		//获取个人信息
		String person = "";
		person = doc.getElementsByClass("ml20").removeAttr("a").removeAttr("em").text();
		entity.setPerson(person);
		System.out.println(person);
		
		
		//获取电话url
		
		String phone = getHidePhone(url);
		entity.setTelephone(phone);
		
		entity.setCreationDate(System.currentTimeMillis());
		entity.setModificationDate(System.currentTimeMillis());
		
		
		System.out.println(entity.toUpdateSql());
		JDBCTemplate.save(entity);
	
		return true;
	}

	@Override
	public int totalPageCount() throws Exception {
		
		return maxPageNum;
	}

	@Override
	public boolean isNewPage(int page, long lastCrawlDate) throws Exception {
		
		String url = DOMAIN + "/chuzu/"+ "pn"+page;
		
		Document doc = this.doGet(url);
	
		String title = doc.getElementsByTag("title").get(0).text();
		
		if(title.contains("验证码")){
			//logger.error("抓取58同城出租房需要验证码校验");
			throw new DataTaskException(202, "爬虫已被发现", null);
		}
		
		Element infolist = doc.getElementById("infolist");
		
		Element houseList = infolist.getElementsByClass("tbimg").get(0);
		
		Elements trs = houseList.getElementsByTag("tr");
		
		for(int j=0;j<trs.size();j++){
			
			Element house = trs.get(j);
			
			String houseDetailUrl = house.getElementsByTag("h1").get(0).getElementsByTag("a").get(0).attr("href");
	
			if(isCrawled(houseDetailUrl) == null)//判断是否已经抓取过了
				return true;
		}

		return false;
	}
	
	
	private Zt58PersonChuzu isCrawled(String houseDetailUrl) {

		Zt58PersonChuzuQuery query = new Zt58PersonChuzuQuery();
		query.setUrl(houseDetailUrl);
		
		Zt58PersonChuzu house = (Zt58PersonChuzu) JDBCTemplate.findOne(query);
		
		return house;
	}

	private String getHidePhone(String url){
		
		try {
			
			WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
			client.getOptions().setJavaScriptEnabled(true);    //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
			client.getOptions().setCssEnabled(false);
			client.setAjaxController(new NicelyResynchronizingAjaxController());
			client.getOptions().setThrowExceptionOnScriptError(false);        

			HtmlPage page = client.getPage(url);

			HtmlAnchor btn = (HtmlAnchor) page.getElementById("j_connect_btn");
			
			HtmlPage page2 = btn.click();
			
			String img = page2.getElementById("t_phone").getElementsByTagName("img").get(0).getAttribute("src");
			
			return img;
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	private static Double findDouble(String s){
		
		String reg = "[0-9]+\\.{0,}[0-9]{0,}";
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = p.matcher(s);
		
		String num = "";
		
		while(m.find()){
			num += m.group();
		}
		
		if(num.equals(""))
			return null;
		
		return Double.parseDouble(num);
		
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
	
	private static Double findAreas(String content){
		
		String reg = "[0-9]+\\.{0,}[0-9]{0,}(平|平米|平方米|㎡)";
		
		return matchDouble(content, reg);
		
	}
	
	private static String findHouseType(String content){
		
		String reg = "(普通住宅|商住两用|公寓|写字楼|别墅)";
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = p.matcher(content);
		
		while(m.find()){
			
			String result = m.group();
	
			return result;
		}
		
		return null;
	}
	
	private static String findDecoration(String content){
		
		String reg = "(毛胚|简装修|简单装修|中等装修|精装修|豪华装修)";
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = p.matcher(content);
		
		while(m.find()){
			
			String result = m.group();
	
			return result;
		}
		
		return null;
	}
	
	private static String findDirection(String content){
		
		String reg = "朝向{1}[\u4E00-\u9FFF]{1,}";
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = p.matcher(content);
		
		while(m.find()){
			
			String result = m.group();
	
			return result.replaceAll("朝向", "");
		}
	
		return null;
	}
	
	private static String findSupport(String content){
		
		String reg = "[\u4E00-\u9FFF]{1,}";
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = p.matcher(content);
		
		String result = "";
		
		while(m.find()){
			
			result += m.group()+",";
		}
		
		if(result.endsWith(","))
			result = result.substring(0, result.length()-1);
		
		return result;
	}
	
	private static Integer findWashrooms(String content){
		
		String reg = "[0-9]{1}卫";
		
		return matchInteger(content, reg);
	
	}
	
	private static Integer findKitchenrooms(String content){
		
		String reg = "[0-9]{1}厨";
		
		return matchInteger(content, reg);
	
	}
	
	private static Integer findLivingrooms(String content){
	
		String reg = "[0-9]{1}厅";
		
		return matchInteger(content, reg);
	
	}
	
	
	private static Integer findBedrooms(String content){
	
		String reg = "[0-9]{1}(房|室)";
		
		return matchInteger(content, reg);
		
	}
	
	private static Double matchDouble(String content, String reg){
		

		Pattern p = Pattern.compile(reg);
		
		Matcher m = p.matcher(content);
		
		while(m.find()){
			
			String result = m.group();
	
			return findDouble(result);
		}
		
		return null;
	}
	
	private static Integer matchInteger(String content, String reg){
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = p.matcher(content);
		
		while(m.find()){
			
			String result = m.group();
	
			return findInteger(result);
		}
		
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		
		SpiderRunParam param = new SpiderRunParam();
		param.setProxy(false);

		Five8PersonChuzuSpider task = new Five8PersonChuzuSpider();
		task.setRunParam(param);
		
		task.crawlDetailInfo("http://sz.58.com/hezu/23168223103647x.shtml?psid=151237101188944026680339696&entinfo=23168223103647_0&PGTID=151237101188944026680339696&ClickID=7&iuType=r_0");
		
		
	}
	
	
}
