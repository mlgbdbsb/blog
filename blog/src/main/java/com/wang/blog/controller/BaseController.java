package com.wang.blog.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wang.blog.exception.InsertException;
import com.wang.blog.exception.ServiceException;
import com.wang.blog.exception.UserExistedException;
import com.wang.blog.util.JsonResult;

public class BaseController {

	protected static final int SUCCESS = 200;
	
	@ExceptionHandler(ServiceException.class)
	public JsonResult<Void> handlerException(Throwable e) {
		JsonResult<Void> json = new JsonResult<Void>(e);
		
		if(e instanceof UserExistedException) {
			json.setState(400);
		}else if(e instanceof InsertException) {
			json.setState(500);
		}
		return json;
	}
	
}
