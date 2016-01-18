package com.xiaojiang.exception;

public class DataTaskException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private int errcode;
	private String message;
	
	public DataTaskException(int errcode, String message,  Throwable e){
		
		this.errcode = errcode;
		this.message = message;
	}
	
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
