package cn.ybzy.qihangkt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.pagehelper.PageInfo;

import cn.ybzy.qihangkt.model.Role;
import cn.ybzy.qihangkt.model.User;
import cn.ybzy.qihangkt.service.RoleService;
import cn.ybzy.qihangkt.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@GetMapping("/admin/admin.html")
	public String admin() {
		return "admin/admin";
	}

	@GetMapping("/admin/userManager.html")
	public String userManager(Model model, Integer pageNum, Integer pageSize) {
		// 获取到所有的角色记录信息
		List<Role> roles = roleService.selectAll();
		// System.out.println("role" + roles);
		// 注入到User.jsp视图页面
		model.addAttribute("allRoles", roles);
//		System.out.println("拿到的角色信息11" + roles);
//		System.out.println("页面标号" + pageNum);
//		System.out.println("页面数量" + pageSize);
		//编辑用户信息跳转页面，需要添加信息
		if(pageNum == null || pageNum == 0) pageNum = 1;
		if(pageSize == null || pageSize == 0) pageSize =10;
		

		PageInfo<User> users = userService.selectUsersByPager(pageNum, pageSize);
		model.addAttribute("userDatasByPager", users);

		// 获取到所有的用户记录信息,注入到User.jsp视图页面
		// List<User> users = userService.selectRelevanceUsers();
		// model.addAttribute("allUser", users);
		// System.out.println("拿到的关联查询的用户信息是22" + users);
		return "admin/user";
	}

	@GetMapping("/admin/roleManager.html")
	public String roleManager() {
		return "admin/role";
	}

	@GetMapping("/admin/resManager.html")
	public String resManager() {
		return "admin/resources";
	}

}
