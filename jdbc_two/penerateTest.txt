package com.yh.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.yh.jdbc.utils.JDBCUtil;

public class PenerateTest {
	//获取自动生成的自建（两种方法）
	public static void main(String[] args) throws Exception {
		// 1.连接数据库，编写sql语句
		Connection conn = JDBCUtil.getConn();
		String sql = "insert into student (name,age) values (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, "红红1");
		ps.setInt(2,14);
		//2.结果集如果查到了就输出
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			int id = rs.getInt(1);
			System.out.println(id);
		}
		JDBCUtil.close(conn, ps, rs);
	}
	
	//第二种获取自动生成的id的方法
	void test() throws Exception {
		// 1.连接数据库，编写sql语句
		Connection conn = JDBCUtil.getConn();
		//插入数据的sql语句，数据库会自动生成唯一的id，作为精确查找数据的标识
		String sql = "insert into student (name,age) values ('红红1',14)";
		Statement st = conn.createStatement();
		// 设置可以获取主键
		st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		// 获取自动生成的id
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			int id = rs.getInt(1);
			System.out.println(id);
		}
		JDBCUtil.close(conn, st, rs);

	}

}
