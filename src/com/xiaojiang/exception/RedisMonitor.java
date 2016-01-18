package com.xiaojiang.exception;


public class RedisMonitor {
	private static RedisMonitor instance = new RedisMonitor();
    
    private static int total = 0;
    private static int success = 0;
    private static int fail = 0;
    
    public static RedisMonitor getInstance(){
    	return instance;
    }
    
    public static void access(){
    	total++;
    }
    
    public static void success(){
    	success++;
    }
    
    public static void fail(){
    	fail++;
    }
    
    public int getTotal(){
    	return total;
    }
    
    public int getSuccess(){
    	return success;
    }
    
    public int getFail(){
    	return fail;
    }
}
