package com.wang.blog;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void a() throws SQLException {
		Connection conn = dataSource.getConnection();
		System.err.println(conn);
	}

}
