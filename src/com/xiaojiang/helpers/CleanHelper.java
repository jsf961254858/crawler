package com.xiaojiang.helpers;

import java.util.List;

import com.xiaojiang.bean.BeanBase;
import com.xiaojiang.util.JDBCTemplate;
import com.xiaojiang.util.QueryBase;


public class CleanHelper {
	
	public static List<BeanBase> findList(QueryBase query){
		
		query.setCustomQueryCondotion("(is_cleaned is null or is_cleaned = 0) order by modification_date asc limit 0, 100");
		
		return (List<BeanBase>) JDBCTemplate.findList(query);
	}
}
