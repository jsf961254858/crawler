package com.xiaojiang.crawlertest;

import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class QQEmailLogin {

	public static boolean crawlDetailInfo(String url) throws Exception {
		
		WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
		client.getOptions().setJavaScriptEnabled(true);    //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		client.getOptions().setThrowExceptionOnScriptError(false);   
		  
		HtmlPage page1 = client.getPage(url);

		List<FrameWindow> frames = page1.getFrames();
		
		HtmlPage page2= (HtmlPage) frames.get(0).getEnclosedPage();  
		
		
		HtmlForm loginForm = page2.getFormByName("loginform");
		
		HtmlInput u = loginForm.getInputByName("u");
		u.setValueAttribute("961254858@qq.com");
        HtmlInput p = loginForm.getInputByName("p");
        p.setValueAttribute("\"jiangsanfeng\"");
        
        HtmlSubmitInput loginButton = loginForm.getInputByValue("Sign In");
        
        HtmlPage result = loginButton.click();
        
        String resultUrl = result.getUrl().toString();
		System.out.println(resultUrl);
		
		
		
		//天涯登录
		// 拿到这个网页 
		HtmlPage page = client.getPage(url); 

		// 填入用户名和密码 
		HtmlInput username = (HtmlInput) page.getElementById("userName"); 
		username.type("xiaojiang1111"); 
		HtmlInput password = (HtmlInput) page.getElementById("password"); 
		password.type("jsf961254858"); 

		// 提交 
		HtmlButton submit = (HtmlButton) page.getElementById("loginBtn"); 
		HtmlPage nextPage = submit.click(); 
		
		String nextPageURL = nextPage.getUrl().toString();
		System.out.println(nextPageURL);
		//System.out.println(nextPage.asXml()); 
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		
		String url ="https://mail.qq.com/";
		//String url = "http://passport.tianya.cn/login.jsp";
		
		crawlDetailInfo(url);
		
	}

	
	
}
