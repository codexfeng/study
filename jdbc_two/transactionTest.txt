package com.yh.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yh.jdbc.utils.JDBCUtil;

public class TransactionTest {
	public static void main(String[] args) throws Exception {
		
//		 检查zs账户的余额
//		 减少zs的账户1000
//		 增加ls账户1000
	
		//1.连接数据库
		Connection conn = JDBCUtil.getConn();
		//创建语句
		String sql = "select * from accout where name = ? and money > ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "zs");
		ps.setInt(2, 1000);
		//执行语句
		ResultSet rs = ps.executeQuery();
		if(!rs.next()) {
			throw new RuntimeException("没钱了耶");
		}
		//2.减少zs账户
		sql = "update accout set money = money - ? where name = ? ";
		ps = conn.prepareStatement(sql);
		ps.setDouble(1,1000);
		ps.setString(2,"zs");
		ps.executeUpdate();
		//3.增加ls账户
		sql = "update accout set money = money + ?where name = ?";
		ps = conn.prepareStatement(sql);
		ps.setDouble(1,1000);
		ps.setString(2,"ls");
		ps.executeUpdate();
		//4.释放资源
		JDBCUtil.close(conn, ps, rs);
		
	}
	
	

}
