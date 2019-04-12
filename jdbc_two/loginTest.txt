package com.yh.jdbc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import org.junit.Test;


public class LoginTest {
	@Test//测试结果
	public void test() throws Exception {
		System.out.println(this.login("张三", "123"));
	}
	
	String login(String userName, String pwd) throws Exception {
		// 1.连接数据库
		Connection conn = JDBCUtil.getConn();
		// 2.查询用户名和密码，同时相同时正确
		String sql = "select * from user where name = ? and pwd = ?";
		// 3.执行sql语句
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, userName);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) { 
			//关闭资源
			JDBCUtil.close(conn, ps, rs);
			return "登录成功！";
		} else {
			JDBCUtil.close(conn, ps, rs);
			return "登录失败！";
		}
	}
}
