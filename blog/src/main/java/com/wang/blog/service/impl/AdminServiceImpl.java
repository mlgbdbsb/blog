package com.wang.blog.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.wang.blog.exception.InsertException;
import com.wang.blog.exception.UserExistedException;
import com.wang.blog.mapper.AdminMapper;
import com.wang.blog.model.entity.Admin;
import com.wang.blog.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public void init(Admin admin) {
	
		Admin result = findByUsername(admin.getUsername());
		System.err.println(result);
		System.err.println(admin.getUsername());
		if(result != null) {
			throw new UserExistedException("已经初始化过了");
		}
		//初始化
		Date now = new Date();
		admin.setGmtCreate(now);
		admin.setGmtModified(now);
		String salt = UUID.randomUUID().toString();
		String md5Password = getMd5Password(salt, admin.getPassword());
		admin.setPassword(md5Password);
		admin.setSalt(salt);
		save(admin);
	}
	
	private void save(Admin admin) {
		Integer rows = adminMapper.save(admin);
		if(rows!=1) {
			throw new InsertException("严重错误，联系管理员");
		}
	}
	
	private Admin findByUsername(String username) {
		return adminMapper.findByUsername(username);
	}

	private String getMd5Password(String salt,String password) {
		String md5Password = salt + password + salt;
		for(int i=0;i<5;i++) {
			md5Password = DigestUtils.md5DigestAsHex(md5Password.getBytes());
		}
		return md5Password;
	}
	
}
