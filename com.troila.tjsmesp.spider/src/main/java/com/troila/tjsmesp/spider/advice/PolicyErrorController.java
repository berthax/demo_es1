package com.troila.tjsmesp.spider.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.troila.tjsmesp.spider.exception.HandlerException;
import com.troila.tjsmesp.spider.exception.PError;

/**
 * 用于对Controller出现异常时的统一处理
 * @author xuanguojing
 *
 */

@ControllerAdvice
public class PolicyErrorController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 处理抛出的特定处理异常的情况 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HandlerException.class)
	public ResponseEntity<PError> handlerError(HandlerException e){
		PError error = new PError(e.getCode(),e.getMessage());
		logger.info("捕获异常，并进行处理……，异常内容为：",e);
		return new ResponseEntity<PError>(error,HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * 处理Exception异常的情况,直接认为是服务器内部出错了，
	 * 只要不是显示抛出的HandlerException，其他的异常都由这个异常处理方法来接收
	 * @param e
	 * @return
	 */	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerAllError(Exception e){
		logger.info("捕获异常，并进行处理……，异常内容为：",e);
		return new ResponseEntity<String>("服务器内部异常，请联系管理员",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
