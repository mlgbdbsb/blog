package com.wang.blog.util;

public class JsonResult<T> {

	private String message;
	private Integer state;
	private T data;
	
	public JsonResult(Integer state) {
		this.state = state;
	}
	public JsonResult(Throwable e) {
		this.message = e.getMessage();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
