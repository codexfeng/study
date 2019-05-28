package cn.ybzy.qihangkt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ybzy.qihangkt.dao.BaseDao;
import cn.ybzy.qihangkt.dao.UserDao;
import cn.ybzy.qihangkt.dao.UserRoleDao;
import cn.ybzy.qihangkt.model.User;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public BaseDao getBaseDao() {
		return userDao;
	}

	@Override
	public void addUser(User user, Integer[] roleIds) {
		// 添加用户
		this.addForNotMatch(new Object[] { "username", "password", "email", "phone", "enable", "add_date" },
				new Object[] { user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), 1,
						new Date() });
		// 根据新添加进去的名称，查询出刚刚添加的用户数据，需要一个方法，根据用户名获取用户对象（整条数据记录）
		User u = userDao.getUserByName(user.getUsername());
		// 拿到查询的数据
		System.out.print(u);
		// 遍历数据库，拿到数据放在jsp页面
		for (Integer rid : roleIds) {
			userRoleDao.add("t_user_role", new Object[] { null, u.getId(), rid });
		}
	}

	@Override
	public List<User> selectRelevanceUsers() {

		return userDao.selectRelevanceUsers();

	}

	@Override
	public PageInfo<User> selectUsersByPager(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		List<User> userDatas = userDao.selectRelevanceUsers();
		PageInfo<User> info = new PageInfo<>(userDatas);

		return info;
	}

	@Override
	public User selectRelUserByUid(Integer uid) {
		User user = userDao.getUserByUid(uid);

		return user;
	}

	@Override
	public void updateUser(User user, Integer[] roleIds) {
		//修改数据，判断数据
		if("".equals(user.getPassword().trim())){
			user.setPassword(null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
