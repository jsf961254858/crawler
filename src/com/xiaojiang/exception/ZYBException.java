package com.xiaojiang.exception;

public abstract class ZYBException extends Exception {

private static final long serialVersionUID = 1L;
	
	private int ret;
	private int errcode;
	private String reason;//显示给用户看的错误提示信息
	
	public ZYBException(int ret, int errcode, String reason, String message, Throwable t){
		super(message, t);
		
		this.ret = ret;
		this.errcode = errcode;
		this.reason = reason;
	}
	
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
