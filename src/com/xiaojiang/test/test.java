package com.xiaojiang.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiaojiang.crawler.bean.Characteristic;
import com.xiaojiang.crawler.bean.CityCommunity;
import com.xiaojiang.crawler.query.CharacteristicQuery;
import com.xiaojiang.crawler.query.CityCommunityQuery;
import com.xiaojiang.util.JDBCTemplate;

public class test {

	public static void main(String[] args) {
		
		//从city_community表中取出小区信息
		List<CityCommunity> communitys = new ArrayList<CityCommunity>();
		CityCommunityQuery query = new CityCommunityQuery();
		communitys = (List<CityCommunity>) JDBCTemplate.findList(query); 
		
		//根据小区的id去characteristic表中查询响应的数据
		for(CityCommunity c : communitys){
			
			List<Characteristic> ch_tot = new ArrayList<Characteristic>(); //总房源
			List<Characteristic> ch_one = new ArrayList<Characteristic>(); //一室
			List<Characteristic> ch_two = new ArrayList<Characteristic>(); //两室
			List<Characteristic> ch_thr = new ArrayList<Characteristic>(); //三室
			List<Characteristic> ch_fou = new ArrayList<Characteristic>(); //四室
			List<Characteristic> ch_fiv = new ArrayList<Characteristic>(); //四室以上
		
			//计算总价
			CharacteristicQuery query_total = new CharacteristicQuery();
			query_total.setCommunity(String.valueOf(c.getId()));
			
			
			ch_tot = (List<Characteristic>) JDBCTemplate.findList(query_total);
			double tot_sum = 0;
			double total_avg = 0;
			if(ch_tot.size() > 0){
				
				for(Characteristic character : ch_tot){
					
					tot_sum += Double.valueOf(character.getPrice());
					
					if("1".equals(character.getRoom())){
						ch_one.add(character);
					}else if("2".equals(character.getRoom())){
						ch_two.add(character);
					}else if ("3".equals(character.getRoom())){
						ch_thr.add(character);
					}else if ("4".equals(character.getRoom())){
						ch_fou.add(character);
					}else {
						ch_fiv.add(character);
					}
				}
				total_avg = tot_sum/ch_tot.size();
			}
			
			//计算一室 在characteristics中找一室的数据
			double min_one = 0;
			double max_one = 0;
			double avg_one = 0;
			double one_sum = 0;
			
			if(ch_one.size() > 0){
				
				double temp =0;
				min_one = Double.MAX_VALUE;
				max_one = -1;
				
				for(Characteristic c_one : ch_one){
					temp = Double.valueOf(c_one.getPrice());
					if(min_one > temp)
						min_one = temp;
					if(max_one < temp)
						max_one = temp;
					one_sum += temp;
				}
				avg_one = one_sum/ch_one.size();
			}
			
			//计算二室
			double min_two = 0;
			double max_two = 0;
			double avg_two = 0;
			double two_sum = 0;
			
			if(ch_two.size() > 0){
				
				double temp =0;
				min_two = Double.MAX_VALUE;
				max_two = -1;
				
				for(Characteristic c_two : ch_two){
					temp = Double.valueOf(c_two.getPrice());
					if(min_two > temp)
						min_two = temp;
					if(max_two < temp)
						max_two = temp;
					two_sum += temp;
				}
				avg_two = two_sum/ch_two.size();
			}
			
			//计算三室
			double min_thr = 0;
			double max_thr = 0;
			double avg_thr = 0;
			double thr_sum = 0;
			
			if(ch_thr.size() > 0){
				
				double temp =0;
				min_thr = Double.MAX_VALUE;
				max_thr = -1;
				
				for(Characteristic c_thr : ch_thr){
					temp = Double.valueOf(c_thr.getPrice());
					if(min_thr > temp)
						min_thr = temp;
					if(max_thr < temp)
						max_thr = temp;
					thr_sum += temp;
				}
				avg_thr = thr_sum/ch_thr.size();
			}
			
			//计算四室
			
			double min_fou = 0;
			double max_fou = 0;
			double avg_fou = 0;
			double fou_sum = 0;
			
			if(ch_fou.size() > 0){
				
				double temp =0;
				min_fou = Double.MAX_VALUE;
				max_fou = -1;
				
				for(Characteristic c_fou : ch_fou){
					temp = Double.valueOf(c_fou.getPrice());
					if(min_fou > temp)
						min_fou = temp;
					if(max_fou < temp)
						max_fou = temp;
					fou_sum += temp;
				}
				avg_fou = fou_sum/ch_fou.size();
			}
			
			//计算四室以上
			double min_fiv = 0;
			double max_fiv = 0;
			double avg_fiv = 0;
			double fiv_sum = 0;
			
			if(ch_fiv.size() > 0){
				
				double temp =0;
				min_fiv = Double.MAX_VALUE;
				max_fiv = -1;
				
				for(Characteristic c_fiv : ch_fiv){
					temp = Double.valueOf(c_fiv.getPrice());
					if(min_fiv > temp)
						min_fiv = temp;
					if(max_fiv < temp)
						max_fiv = temp;
					fiv_sum += temp;
				}
				avg_fiv = fiv_sum/ch_fiv.size();
			}
			
			//根据需求计算的结果 插入到city_community表中
			/**
			 * 存在一个问题，这张表中会覆盖上一个月的数据
			 * community.setId(c.getId()); 可以不根据id插入 
			 * 
			 */
			
			/**
			 * 服务器那边  应该是从super_property数据库中取出小区的信息
			 * 再在爬虫数据库中 计算响应的业务
			 * 最后再存到一张最终展示的表中
			 */
			CityCommunity community = new CityCommunity();
			community.setId(c.getId());
			community.setTotalAvg(total_avg);
			community.setOneMin(min_one);
			community.setOneMax(max_one);
			community.setOneAvg(avg_one);
			community.setTwoMin(min_two);
			community.setTwoMax(max_two);
			community.setTwoAvg(avg_two);
			community.setThrMin(min_thr);
			community.setThrMax(max_thr);
			community.setThrAvg(avg_thr);
			community.setFouMin(min_fou);
			community.setFouMax(max_fou);
			community.setFouAvg(avg_fou);
			community.setFivMin(min_fiv);
			community.setFivMax(max_fiv);
			community.setFivAvg(avg_fiv);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		    Date d=null;
			try {
				d = sdf.parse("2015-12");
			} catch (ParseException e) {
			}
		    Long date = d.getTime();
			
			community.setDate(date);
			
			//System.out.println(community.toUpdateSql());
			JDBCTemplate.save(community);
		
		}
		
		
		

	}
	
	
}
