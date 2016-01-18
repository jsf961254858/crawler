package com.xiaojiang.exception;


public class RedisException extends ServerException {
	private static final long serialVersionUID = 1L;

	public RedisException(String message, Throwable t){
		super(message, t);
		
		RedisMonitor.fail();
	}
}
