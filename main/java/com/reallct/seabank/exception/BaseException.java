package com.reallct.seabank.exception;

public class BaseException extends Exception {
	private String code;
	private String msg;
	/**
	* @Fields serialVersionUID : εΊεε
	*/
	private static final long serialVersionUID = -1923698225473248702L;
	public BaseException(String code, String msg) {
		super(code);
		this.setCode(code);
		this.setMsg(msg);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
