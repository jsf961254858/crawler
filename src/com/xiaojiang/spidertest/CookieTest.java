package com.xiaojiang.spidertest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.xiaojiang.exception.DataTaskException;
import com.xiaojiang.httpclient.HttpUserAgent;

public class CookieTest {

	
	public static void main(String[] args) throws Exception, IOException {
		
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		
		String newUrl = "http://www.dajie.com/home";
		HttpGet httpGet = new HttpGet(newUrl);
		
		//在页面控制台执行document.cookie
		String cookie = "DJ_RF=empty; DJ_EU=http%3A%2F%2Fwww.dajie.com%2Fhome; DJ_UVID=MTQ2MTkwNzk3NDU4MTg1NjQ2; dj_cap=0564c054acc1ce12402998471ae0af54; regSucceedType=email; dj_auth_v3=MrZrP3TGNRNXCNiOpQY7Ggscf4kjfEEsJzFPDzu3iwi5XtG9tS3Sw-WgChC2DVKL; uchome_loginuser=35375099; USER_ACTION=\"request^AProfessional^AREG^Aregm:crt0^A-\"; send_verify_mail=961254858%40qq.com; login_email=961254858%40qq.com; inbound_tag=true";
		httpGet.addHeader(new BasicHeader("Cookie", cookie));
		
		httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("User-Agent", HttpUserAgent.get()); 
		
		response = client.execute(httpGet);
		String html = formatResponse(response);
		Document doc = Jsoup.parse(html);
		String text = doc.select(".feed-header").get(0).text();
		
		System.out.println(text);
		
		httpGet.releaseConnection();
		
		
		
	}
	
	private static String formatResponse(HttpResponse response) throws Exception {
		
		ByteArrayInputStream bis = null;
		Header contentEncoding = response.getFirstHeader("Content-Encoding");
		
		if(contentEncoding == null){
			return EntityUtils.toString(response.getEntity(),"UTF-8");
		} else {
			
			String charset = "utf-8";
			Header contentType = response.getFirstHeader("Content-Type");
			
			if(contentType != null){
				String contentTypeStr = contentType.getValue();
				if(contentTypeStr != null && !"".equals(contentTypeStr)){
					charset = contentTypeStr.substring(contentTypeStr.indexOf("=") + 1,contentTypeStr.length());
					
				}
			}
			
			String contentEncodingType = contentEncoding.getValue();
			if(contentEncodingType.equalsIgnoreCase("gzip")){
				if(response.toString().contains("soufun"))
					charset = "gb2312";
				
				byte[] bytes = IOUtils.toByteArray(response.getEntity().getContent());
				bis = new ByteArrayInputStream(bytes);
				
				return uncompress(bis ,charset);
			}
			
		}
			
		
		return null;
	}

	
	/**
	 * GZIP解压
	 */
	private static String uncompress(ByteArrayInputStream in, String charset) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while((n = gunzip.read(buffer)) >=0 ){
				out.write(buffer, 0, n);
			}
			return out.toString(charset);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
