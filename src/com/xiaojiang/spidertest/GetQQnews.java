package com.xiaojiang.spidertest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetQQnews {
	
	public static void main(String[] args){

        HttpConnectionManager httpConnectionManager = new HttpConnectionManager();

        String html = httpConnectionManager.getHtml("http://www.qq.com");

        Document doc = Jsoup.parse(html);

        Elements newsList = doc.select("[class=ft fl]").select("ul").select("li").select("a");

        for (Element element : newsList) {

            System.out.println(element.attr("href") + "----" + element.text());

        }

    }
}
