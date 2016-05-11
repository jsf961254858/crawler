package com.xiaojiang.crawlertest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.httpclient.HttpClientRequestHandler;
import com.xiaojiang.httpclient.HttpConnectionManager;
import com.xiaojiang.httpclient.HttpUserAgent;


/**
 * 使用java获取对应API接口返回的json数据
 * @author Administrator
 *
 */
public class HttpRequestUtil {

	/**
	 * 从url中请求获取返回的json串
	 * @param url接口
	 * @return
	 * @throws IOException 
	 */
	public static String HttpRequest(String requestUrl) throws IOException{
		
		StringBuffer sb = new StringBuffer();
		InputStream is = getInputStream(requestUrl);
		
		InputStreamReader isReader = new InputStreamReader(is,"utf-8");
		
		BufferedReader bufferedReader = new BufferedReader(isReader);
		String temp = null;
		
		while((temp = bufferedReader.readLine()) != null){
			sb.append(temp);
		}
		
		bufferedReader.close();
		isReader.close();
		is.close();
		
		return sb.toString();
	}

	/**
	 * 从请求的url中获取返回的数据流
	 * @param requestUrl 对应的url接口
	 * @return InputStream
	 * @throws IOException 
	 */
	private static InputStream getInputStream(String requestUrl) throws IOException {

		URL url = null;
		HttpURLConnection conn = null;
		InputStream in = null;
		
		url = new URL(requestUrl);
		
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		conn.connect();
		
		in = conn.getInputStream();
		
		return in;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		String url = "http://sz.lianjia.com/fangjia/priceTrend/c2411053291137";
//		String result = HttpRequest(url);
//		JSONObject json = new JSONObject(result);
//		Object object = json.getJSONObject("currentLevel")
//				.getJSONObject("dealPrice")
//				.get("total");
//		String s1 = object.toString();
//		String s2 = s1.substring(1, s1.length()-1).replace("\"", "");
//		String[] split = s2.split(",");
//		for(int i = 0;i<split.length;i++){
//			System.out.println(split[i]);
//		}
		HttpClient httpClient = HttpConnectionManager.getHttpClient(null, null);
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("User-Agent", HttpUserAgent.get());
		HttpResponse response = httpClient.execute(httpGet);
		String result  = EntityUtils.toString(response.getEntity(),"GBK");
		JSONObject json = new JSONObject(result);
		Object object = json.getJSONObject("currentLevel")
				.getJSONObject("dealPrice")
				.get("total");
		String s1 = object.toString();
		String s2 = s1.substring(1, s1.length()-1).replace("\"", "");
		String[] split = s2.split(",");
		for(int i = 0;i<split.length;i++){
			System.out.println(split[i]);
		}
		
	}
	
	
	
}
