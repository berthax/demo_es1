package com.troila.tjsmesp.spider.exception;

/**
 * 返回错误信息类
 * @author xuanguojing
 *
 */
public class PError {
	
	private int code;
	private String message;

	public PError(int code, String message) {
		super();
		this.code = code;
		this.message = message;
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
}
