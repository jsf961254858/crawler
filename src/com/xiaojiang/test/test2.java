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

public class test2 {

	public static void main(String[] args) {

		double total_avg = 0;
		double min_one = 0;
		double max_one = 0;
		double avg_one = 0;
		double min_two = 0;
		double max_two = 0;
		double avg_two = 0;
		double min_thr = 0;
		double max_thr = 0;
		double avg_thr = 0;
		double min_fou = 0;
		double max_fou = 0;
		double avg_fou = 0;
		double min_fiv = 0;
		double max_fiv = 0;
		double avg_fiv = 0;

		// 从city_community表中取出小区信息
		List<CityCommunity> communitys = new ArrayList<CityCommunity>();
		CityCommunityQuery query = new CityCommunityQuery();
		communitys = (List<CityCommunity>) JDBCTemplate.findList(query);

		for (CityCommunity c : communitys) {

			CityCommunity community = new CityCommunity();

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

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			//String date = sdf.format(new Date());
			//System.out.println(date);

			Date d = null;
			try {
				d = sdf.parse("2016-1");
			} catch (ParseException e) {

			}
			Long dd = d.getTime();

			community.setDate(dd);
			
			community.setCommunityName(c.getCommunityName());

			// System.out.println(community.toUpdateSql());
			JDBCTemplate.save(community);

		}

	}

}
