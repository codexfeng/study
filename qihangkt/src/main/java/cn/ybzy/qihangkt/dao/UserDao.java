package cn.ybzy.qihangkt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ybzy.qihangkt.model.User;

public interface UserDao extends BaseDao {
	// 不用写重复的方法，只需要写userdao特有的方法

	// 根据用户名获取对象
	public User getUserByName(@Param("username") String username);

	public List<User> selectRelevanceUsers();

	// 通过用户id查询对应的对象，关联上role信息
	public User getUserByUid(@Param("uid")Integer uid);
}
