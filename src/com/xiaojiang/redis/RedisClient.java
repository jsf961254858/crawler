package com.xiaojiang.redis;


import java.util.Random;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisClient {

	private JedisPool pool;
	
	private String auth = null;
	
	public void init(){
		
		System.out.println("redis init");
		
		// 池基本配置 
		JedisPoolConfig config = new JedisPoolConfig();
	    
		//auth = "xiaojiang";
        config.setMaxIdle(20);
        config.setMaxTotal(200);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(false);
     
        pool = new JedisPool(config, "127.0.0.1",6379,5000);
	}
	
	public synchronized Jedis getClient(){
		
		if(pool == null)
			init();
		
		Jedis jedis = pool.getResource();
		//jedis.auth(auth); 
		//默认是没有密码的  要是设置密码需要redis-server.exe D:\redis-2.6\redis.conf读取配置文件中的requirepass
		
		return jedis;
	}
	
	public void returnJedisPool(Jedis jedis){
		pool.returnResourceObject(jedis);
	}
	
	public static void main(String[] args) {
		/*
		RedisClient redisClient = new RedisClient();
		Jedis jedis = redisClient.getClient();
		if(jedis == null){
			System.out.println("没有取到redis连接");
		}else{
			//System.out.println("链接到redis");
			String value = jedis.hget(new Random().nextInt(1000000)+"","");
			System.out.println(value);
		}
		*/
	}
	
}
