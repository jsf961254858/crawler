package com.xiaojiang.exception;


public class ServerException extends ZYBException {

private static final long serialVersionUID = 1L;
	
	public static final int ERROR_CODE = 401;
	public static final String PROMPT = "系统维护中，请稍后再试！";

	public ServerException(String message, Throwable cause) {
		super(4, ERROR_CODE, PROMPT, message, cause);
		
		ServerMonitor.fail();
	}

	public ServerException(String message) {
		super(4, ERROR_CODE, PROMPT, message, null);
		
		ServerMonitor.fail();
	}

}
