package com.yh.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.yh.jdbc.utils.JDBCUtil;

public class BatchTest {

	public static void main(String[] args) throws Exception {
		  
		//1.连接数据库，编写sql语句，预编译语句
		Connection conn = JDBCUtil.getConn();
		String sql = "insert into student (name,age) values (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		//计算sql的执行时间
		long begin = System.currentTimeMillis();
			//循环输入数据
			for(int i = 0; i <1000; i++) {
				ps.setString(1, "雅雅");
				ps.setInt(2, 15);
				//添加批处理
				ps.addBatch();
			}
			//执行语句
			ps.executeBatch();
		long end = System.currentTimeMillis();
		
		long time = end - begin;
		System.out.println(time);
	}
}
