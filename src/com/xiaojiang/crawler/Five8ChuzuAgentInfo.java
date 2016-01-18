package com.xiaojiang.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaojiang.AbstractSpider;
import com.xiaojiang.SpiderRunParam;
import com.xiaojiang.crawler.bean.Zt58ChuzuAgentinfo;
import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.util.JDBCTemplate;

public class Five8ChuzuAgentInfo extends AbstractSpider{

	@Override
	public void group() throws Exception {
		
		List<String> conditions = new ArrayList<String>();
		String[] cityZoneUrls = {
				//南山
				"http://sz.58.com/baishizhou/chuzu/1/",  //白石洲
				"http://sz.58.com/dachong/chuzu/1/",     //大冲
				"http://sz.58.com/guimiaolukou/chuzu/1/",//桂庙路口
				"http://sz.58.com/haishangshijie/chuzu/1/",//海上世界
				"http://sz.58.com/haiwangdasha/chuzu/1/",//海王大厦
				"http://sz.58.com/houhai/chuzu/1/",  //后海
				"http://sz.58.com/huaqiaocheng/chuzu/1/", //华侨城
				"http://sz.58.com/kejiyuan/chuzu/1/",  //科技园
				"http://sz.58.com/nanshanquzhengfu/chuzu/1/", //南山区政府
				"http://sz.58.com/nanshanyiyuan/chuzu/1/", //南山医院
				"http://sz.58.com/nanshanzhoubian/chuzu/1/", //南山周边
				"http://sz.58.com/nantou/chuzu/1/", //南头
				"http://sz.58.com/nanxinlukou/chuzu/1/", //南新路口
				"http://sz.58.com/nanyou/chuzu/1/", //南油
				"http://sz.58.com/qianhai/chuzu/1/", //前海
				"http://sz.58.com/shekou/chuzu/1/", //蛇口
				"http://sz.58.com/shendabeimen/chuzu/1/", //深大北门
				"http://sz.58.com/shenzw/chuzu/1/", //深圳湾
				"http://sz.58.com/taoyuancun/chuzu/1/", //桃源村
				"http://sz.58.com/xililu/chuzu/1/", //西丽
				"http://sz.58.com/szzhongxinqu/chuzu/1/" //中心区
				
				};	
				//户型id
		for(int i=0;i<cityZoneUrls.length;i++){
			
			String seedUrl = cityZoneUrls[i]+"pn1/";
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
	public int totalPageCount() throws Exception {
		return 70;
	}
	
	@Override
	public void crawlUrl(int pageNum) throws Exception {
		String url = this.getQueryCondition().replace("i31", "i3"+pageNum);
		//String url ="http://sz.58.com/baishizhou/chuzu/1/".replace("pn1", "pn"+pageNum);
		Document doc = this.doGet(url);
		
		String title = doc.getElementsByTag("title").get(0).text();
		
		if(title.contains("验证码")){
			//logger.error("抓取58同城出租房需要验证码校验");
			throw new DataTaskException(202, "爬虫已被发现", null);
		}
	
		Element infolist = doc.getElementById("infolist");
		
		Element houselist = infolist.getElementsByClass("tbimg").get(0);
		
		Elements trs = houselist.getElementsByTag("tr");
		
		for(int j = 0; j<trs.size()-1;j++){
			
			Element house = trs.get(j);
			
			String houseDetailUrl = house.getElementsByTag("h1").get(0).getElementsByTag("a").attr("href");
			
			//System.out.println(houseDetailUrl);
			
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
		
		/*
		 * 合租和出租有两种排版
		 */
		
		Zt58ChuzuAgentinfo entity = new Zt58ChuzuAgentinfo();
		entity.setUrl(url);
		
		//获取title
		Elements bigtitle = doc.getElementsByClass("bigtitle");
		if(bigtitle != null && bigtitle.size() > 0){
			String houseTitle = bigtitle.get(0).text().replaceAll("/n", "");
			entity.setTitle(houseTitle);
		}
		
		Element suUl = doc.getElementsByClass("suUl").get(0);
		
		Elements lis = suUl.getElementsByTag("li");
		
		for(Element li : lis){
			
			Elements cols = li.getElementsByClass("su_tit");
			if(cols == null || cols.size() == 0)
				continue;
			
			Elements values = li.getElementsByClass("su_con");
		
			String col = cols.get(0).text().trim();
			
			if(col.equals("价格")){
				String price = values.get(0).getElementsByClass("bigpri").get(0).text();
				entity.setPrice(price);
			}else if(col.equals("概况")){
				String value = values.get(0).text().trim();
				entity.setDetail(value);
			}else if(col.equals("楼层")){
				String value = values.get(0).text().trim();
				entity.setFloor(value);
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
				String value = values.get(0).text().trim().replace("看中了，一键搬家", "");
				entity.setAddress(value);
			}else if(col.equals("联系")){
				String agent=li.getElementsByTag("span").text();
				entity.setAgent(agent);
			}else if(col.equals("整体")){
				String value = values.get(0).text().trim();
				entity.setDetail(value);
			}else if(col.equals("出租")){
				String value = values.get(0).text().trim();
				entity.setDetail(entity.getDetail()+value);
			}
			
		}
		
		//经纪人电话
		String phone = doc.getElementById("t_phone").text();
		entity.setAgenttel(phone);
		
		entity.setCreationDate(System.currentTimeMillis());
		entity.setModificationDate(System.currentTimeMillis());
		entity.setIsCleaned(false);
		
		
		//System.out.println(entity.toUpdateSql());
		JDBCTemplate.save(entity);
		
		return true;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		SpiderRunParam runParam = new SpiderRunParam();
		runParam.setProxy(false);
		
		Five8ChuzuAgentInfo task = new Five8ChuzuAgentInfo();
		task.setRunParam(runParam);
		
		//task.crawlUrl(1);
		//http://sz.58.com/hezu/21410895122208x.shtml?psid=156184806189348848189094114&entinfo=21410895122208_0
		//http://sz.58.com/zufang/21695921330462x.shtml?psid=156184806189348848189094114&entinfo=21695921330462_0
		//task.crawlDetailInfo("http://sz.58.com/zufang/21695921330462x.shtml?psid=156184806189348848189094114&entinfo=21695921330462_0");
		task.crawlDetailInfo("http://sz.58.com/hezu/21410895122208x.shtml?psid=156184806189348848189094114&entinfo=21410895122208_0");
	}
	
}
