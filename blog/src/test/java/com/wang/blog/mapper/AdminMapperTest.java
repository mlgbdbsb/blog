package com.wang.blog.mapper;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wang.blog.model.entity.Admin;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminMapperTest {

	@Autowired
	private AdminMapper mapper;
	
	@Test
	public void save() {
		Admin admin = new Admin();
		admin.setGmtCreate(new Date());
		admin.setGmtModified(new Date());
		admin.setPassword("root");
		admin.setSalt(UUID.randomUUID().toString());
		admin.setUsername("root");
		mapper.save(admin);
	}
	
	@Test
	public void findByName() {
		String username = "root";
		Admin user = mapper.findByUsername(username);
		System.err.println(user);
	}
	
}
