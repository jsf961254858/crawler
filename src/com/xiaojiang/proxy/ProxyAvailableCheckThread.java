package com.xiaojiang.proxy;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.xiaojiang.bean.DataTaskProxy;
import com.xiaojiang.bean.DataTaskProxyQuery;
import com.xiaojiang.util.JDBCTemplate;


public class ProxyAvailableCheckThread {

	
	public void run(){
		Timer timer = new Timer();
		//延迟15分钟执行，每15分钟执行一次
		timer.schedule(new CheckProxyTask(), 15*60*1000, 15*60*1000);
	}
	
	
	class CheckProxyTask extends TimerTask{

		@Override
		public void run() {
			//查找没有被拉黑，但是失败次数>=100 并且正确率小于10%的 代理
			List<DataTaskProxy> proxys =  getDataTaskProxys();
			
			if(proxys == null || proxys.size() == 0)
				return;
			
			DataTaskProxy bp = new DataTaskProxy();
			bp.setIsBlack(true);
			bp.setModificationDate(System.currentTimeMillis());
			
			//没有被拉黑，但是失败次数>=100 并且正确率小于10%的 代理    拉黑
			for(DataTaskProxy proxy : proxys){
				
				bp.setId(proxy.getId());
				
				JDBCTemplate.save(bp);
				
			}
			
		}
		
		/**
		 * 查找没有被拉黑，但是失败次数>=100 并且正确率小于10%的 代理
		 * @return
		 */
		private List<DataTaskProxy> getDataTaskProxys(){
			
			DataTaskProxyQuery query = new DataTaskProxyQuery();
			query.setIsBlack(false);
			query.setCustomQueryCondotion("err_num >= 100 and succ_num / (succ_num + err_num) < 0.10");
			
			return (List<DataTaskProxy>) JDBCTemplate.findList(query);
		}
		
	}
}
