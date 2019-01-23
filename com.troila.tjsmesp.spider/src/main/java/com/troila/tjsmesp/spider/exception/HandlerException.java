package com.troila.tjsmesp.spider.exception;

import com.troila.tjsmesp.spider.constant.ErrorInfo;

/**
 * 处理请求过程的异常类
 * @author xuanguojing
 *
 */
public class HandlerException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;

	public HandlerException(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public HandlerException(ErrorInfo errorInfo) {
		this.code = errorInfo.getIndex();
		this.message = errorInfo.getName();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "HandlerException [code=" + code + ", message=" + message + "]";
	}
}
