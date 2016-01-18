package com.xiaojiang.spidertest;



import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.xiaojiang.httpclient.HttpConnectionManager;
import com.xiaojiang.httpclient.HttpUserAgent;

public class IpTest {

	
	/**
	 * 代理IP有效检测
	 *
	 * @param proxyIp
	 * @param proxyPort
	 * @param reqUrl
	 */
	public static Boolean checkProxyIp(String proxyIp, int proxyPort, String reqUrl) {
		
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
			
			if(statuCode == 200)
				return true;
			else
				return false;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if(httpGet != null){
				httpGet.abort();
			}
			
		}
		return false;
	}

	public static void main(String[] args) {
		
		String url = "http://www.baidu.com";
		String proxyIp="120.197.234.164";
		int proxyPort=80;
		
		System.out.println(checkProxyIp(proxyIp,proxyPort,url));
		
	}

}
