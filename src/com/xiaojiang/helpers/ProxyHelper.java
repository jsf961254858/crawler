package com.xiaojiang.helpers;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.xiaojiang.bean.DataTaskProxy;
import com.xiaojiang.bean.DataTaskProxyQuery;
import com.xiaojiang.bean.DataTaskProxyTime;
import com.xiaojiang.util.JDBCTemplate;

public class ProxyHelper {

	/**
	 * 
	 * @param perkProxys
	 * @return List<DataTaskProxy> 可用的代理
	 */
	public static List<DataTaskProxy> refreshProxyPool(Set<Long> perkProxys){
		
		String con = "";
		
		Iterator<Long> it = perkProxys.iterator();
		int i =0;
		while(it.hasNext()){
			Long ip = it.next();
			if(i==0)
				con += "address != " + "\"" + ip + "\"";
			else
				con += " and address != " + "\"" + ip + "\"";
			i++;
		}
		
		if("".equals(con))
			con = null;
		//设置查询条件
		DataTaskProxyQuery query = new DataTaskProxyQuery();
		query.setIsBlack(false); 	//没有拉黑
		query.setCustomQueryCondotion(con);	//
		query.setSortColumn("succ_num / (succ_num + err_num)");
		query.setSortBy("desc");	//降序
		query.setLimit(getProxyCount(false) / 3); //数量为没有拉黑总数除以3
		query.setStart(0);	//设置开始
		
		return (List<DataTaskProxy>) JDBCTemplate.findList(query);
	}

	private static int getProxyCount(boolean isBlack) {

		DataTaskProxyQuery query = new DataTaskProxyQuery();
		query.setIsBlack(isBlack);
		
		int totalCount = JDBCTemplate.findCount(query);
		
		return totalCount;
	}
	
	
	public static void saveProxyResponseTime(long proxyId, long spiderId, long time){
		
		DataTaskProxyTime pt = new DataTaskProxyTime();
		pt.setProxyId(proxyId);
		pt.setSpiderId(spiderId);
		pt.setSpeed(time);
		pt.setCreationDate(System.currentTimeMillis());
		pt.setModificationDate(System.currentTimeMillis());
		
		JDBCTemplate.save(pt);
	}
	
	
	
}
