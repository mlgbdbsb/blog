package com.wang.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.blog.model.entity.Admin;
import com.wang.blog.service.AdminService;
import com.wang.blog.util.JsonResult;

@RestController
@RequestMapping("/")
public class AdminController extends BaseController{

	@Autowired
	private AdminService adminSerivce;
	
	@PostMapping("init")
	public JsonResult<Void> init(Admin admin){
		adminSerivce.init(admin);
		return new JsonResult<Void>(SUCCESS);
	}
	
	
}
