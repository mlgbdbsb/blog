package com.wang.blog.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有实体类的父类
 * 
 * @author wang
 *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5296987289261329444L;
	private Date gmtCreate;
	private Date gmtModified;

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

}
