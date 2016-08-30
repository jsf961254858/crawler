package com.xiaojiang.crawlertest;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class pbc {

	public static void main(String[] args) throws Exception {
		
		WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
		client.getOptions().setJavaScriptEnabled(true);    //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		client.getOptions().setThrowExceptionOnScriptError(false);
		
		//client.getOptions().setProxyConfig(proxyConfig);
		
		String url = "http://wzdig.pbc.gov.cn:8080/dig/ui/search.action?ty=&w=false&f=&dr=true&p=2&sr=score+desc&rp=&advtime=2&advrange=0&fq=&ext=&q=*";
		
		HtmlPage page = client.getPage(url);
		
		System.out.println(page.asXml());
		
		
	}
	
}
