package com.wang.blog.mapper;

import com.wang.blog.model.entity.Admin;

public interface AdminMapper {

	/**
	 * 插入管理员
	 * @param admin
	 * @return
	 */
	Integer save(Admin admin);
	
	/**
	 * 根据用户名查找管理员
	 * @param username
	 * @return
	 */
	Admin findByUsername(String username);
	
}
