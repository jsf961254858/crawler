package com.xiaojiang.httpclient;

import java.io.IOException;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

public class HttpConnectionManager {

	private static HttpParams httpParams;
	private static ClientConnectionManager connectionManager;
	
	//最大连接数
	public final static int MAX_TOTAL_CONNECTIONS = 800;
	
	//获取连接的最大等待时间
	public final static int WAIT_TIMEOUT = 60000;
	
	//每个路由最大连接数
	public final static int MAX_ROUTE_CONNECTIONS = 400;
	
	//连接超时时间
	public final static int CONNECT_TIMEOUT = 60000;
	
	//读取超时时间
	public final static int READ_TIMEOUT = 60000;
	
	static {
		httpParams = new BasicHttpParams();
		// 设置最大连接数
		ConnManagerParams.setMaxTotalConnections(httpParams, MAX_TOTAL_CONNECTIONS);
		// 设置获取连接的最大等待时间
		ConnManagerParams.setTimeout(httpParams, WAIT_TIMEOUT);
		// 设置每个路由最大连接数
		ConnPerRouteBean connPerRoute = new ConnPerRouteBean(MAX_ROUTE_CONNECTIONS);
		ConnManagerParams.setMaxConnectionsPerRoute(httpParams,connPerRoute);
		
		// 设置连接超时时间
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
		// 设置读取超时时间
		HttpConnectionParams.setSoTimeout(httpParams, READ_TIMEOUT);
	
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		registry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

		connectionManager = new ThreadSafeClientConnManager(httpParams, registry);
	}
	
	
	public static HttpClient getHttpClient(String proxyIp, Integer proxyPort){
		
		DefaultHttpClient client = new DefaultHttpClient(connectionManager, httpParams);
		
		if(proxyIp !=null && proxyPort !=null){
			HttpHost proxy = new HttpHost(proxyIp, proxyPort);
			client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		}
		return client;
	}
	
	
	
	
	
	
	
}
