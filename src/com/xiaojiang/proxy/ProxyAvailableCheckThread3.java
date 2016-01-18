package com.xiaojiang.proxy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.xiaojiang.bean.DataTaskProxy;
import com.xiaojiang.bean.DataTaskProxyQuery;
import com.xiaojiang.httpclient.HttpConnectionManager;
import com.xiaojiang.httpclient.HttpUserAgent;
import com.xiaojiang.util.JDBCTemplate;


public class ProxyAvailableCheckThread3 {

	
	public static void main(String[] args) {
		
		ProxyAvailableCheckThread3 test = new ProxyAvailableCheckThread3();
		
		test.run();
		
		//System.out.println(test.checkProxyIp("120.198.231.87", 80));
	}
	
	
	public void run(){
		Timer timer = new Timer();
		//延迟15分钟执行，每15分钟执行一次
		//timer.schedule(new CheckProxyTask(), 15*60*1000, 15*60*1000);
		timer.schedule(new CheckProxyTask(), 1000, 24*60*60*1000);
	}
	
	
	class CheckProxyTask extends TimerTask{

		@Override
		public void run() {
			//获取没有被拉黑代理
			List<DataTaskProxy> proxys =  getDataTaskProxys();
			
			System.out.println(proxys.size());
			
			if(proxys == null || proxys.size() == 0)
				return;
		
			
			DataTaskProxy bp = new DataTaskProxy();
			//bp.setIsBlack(true);
			//bp.setModificationDate(System.currentTimeMillis());
			
			//检测代理是否可用
			for(DataTaskProxy proxy : proxys){
				
				String ip = proxy.getAddress();
				int port = Integer.parseInt(proxy.getPort());
				System.out.println(ip+"    "+port);
				
				//计算响应时间
				long d1 = System.currentTimeMillis();
				
				boolean check = checkProxyIp(ip,port);
				
				long d2 = System.currentTimeMillis();
			
				double connectTime=d2-d1;
				
				if(check){
					bp.setModificationDate(System.currentTimeMillis());
					bp.setConnectTime(connectTime);
					bp.setIsBlack(false);
					bp.setId(proxy.getId());
					JDBCTemplate.save(bp);
				}
				else{
					bp.setModificationDate(System.currentTimeMillis());
					bp.setConnectTime(connectTime);
					bp.setIsBlack(true);
					bp.setId(proxy.getId());
					JDBCTemplate.save(bp);
				}

			}
			
		}
		
		/**
		 * 判断代理是否可用
		 * @param ip
		 * @param port
		 * @return
		 */
		private boolean checkProxyIp(String proxyIp, int proxyPort) {
			
			//使用套接字编程，看代理是否可用
			Socket socket = null;
			try {
				socket = new Socket(proxyIp,proxyPort);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
			
			if(socket == null)
			{
				return false;  
			}
			else {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
		}

		/**
		 * 查找没有被拉黑的代理
		 * @return
		 */
		private List<DataTaskProxy> getDataTaskProxys(){
			
			DataTaskProxyQuery query = new DataTaskProxyQuery();
			query.setIsBlack(false);
			//query.setCustomQueryCondotion("err_num >= 100 and succ_num / (succ_num + err_num) < 0.10");
			
			return (List<DataTaskProxy>) JDBCTemplate.findList(query);
		}
		
	}
	
	private boolean checkProxyIp(String proxyIp, int proxyPort) {
		
		String reqUrl="http://www.sina.com";
		
		HttpClient client = HttpConnectionManager.getHttpClient(proxyIp, proxyPort);  
          
        HttpGet httpGet = new HttpGet(reqUrl);  
        httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");  
        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");  
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");  
        httpGet.setHeader("User-Agent", HttpUserAgent.get());  
          
        try {  
              
            HttpResponse response = client.execute(httpGet);  
            int statuCode = response.getStatusLine().getStatusCode(); 
            
            System.out.println("statuCode: "+statuCode);
            
            if(statuCode == 200)
            	return true; 
            else 
                return false;  
              
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        	//System.out.println(e.getMessage());
        } finally {  
              
            if(httpGet != null){  
                httpGet.abort();  
            }  
              
        }  
        return false;  
	}
}
