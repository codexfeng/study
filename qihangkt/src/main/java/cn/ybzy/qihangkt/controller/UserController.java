package cn.ybzy.qihangkt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.ybzy.qihangkt.model.Role;
import cn.ybzy.qihangkt.model.User;
import cn.ybzy.qihangkt.service.RoleService;
import cn.ybzy.qihangkt.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@PostMapping("admin/addUser.html")
	public String addUser(User user, Integer[] roleIds) {

		// System.out.println(user);
		// System.out.println(Arrays.toString(roleIds));
		userService.addUser(user, roleIds);
		return "redirect:/admin/userManager.html";
	}

	/**
	 * 跟新数据
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/updateUser.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String updateUser(Integer id, HttpServletRequest request) {
		// 通过用户的ID，查询对应的User对象
		User user = userService.selectRelUserByUid(id);
		List<Role> roles = user.getRoles();
		// 获得所有的role对象信息
		List<Role> allRoles = roleService.selectAll();
		String optStr = "";
		for (Role role : allRoles) {
			if (roles.contains(role)) {
				optStr = optStr + "<option selected value=\"" + role.getId() + "\">" + role.getName() + "</option>\r\n";
			} else {
				optStr = optStr + "<option value=\"" + role.getId() + "\">" + role.getName() + "</option>\r\n";
			}
		}

		String path = request.getContextPath();

		return "<div class=\"modal-header\">\r\n"
				+ "					<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\r\n"
				+ "						<span>&times;</span>\r\n" + "					</button>\r\n"
				+ "					<h4 class=\"modal-title\" id=\"myModalLabel\">编辑用户</h4>\r\n"
				+ "				</div>\r\n" + "				<div class=\"modal-body\">\r\n"
				+ "					<form id=\"updateUserForm\" action=\"" + path
				+ "/admin/updateUser.html\" method=\"post\">\r\n"
				+ "                       <input type='hidden' name='id' value='" + user.getId() + "'/>"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>用户名：</label> <input type=\"text\" disabled name=\"username\" class=\"form-control\" value=\""
				+ user.getUsername() + "\">\r\n" + "						</div>\r\n"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>密码（留空表示不修改密码）：</label> <input type=\"password\" name=\"password\" class=\"form-control\" value=\"\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>关联的角色：</label> \r\n"
				+ "							<select name=\"roleIds\" class=\"selectpicker form-control\" multiple>\r\n"
				+ optStr + "							</select>\r\n" + "						</div>\r\n"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>手机号：</label> <input type=\"text\" name=\"phone\" class=\"form-control\" value=\""
				+ user.getPhone() + "\">\r\n" + "						</div>\r\n"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>邮箱：</label> <input type=\"text\" name=\"email\" class=\"form-control\" value=\""
				+ user.getEmail() + "\">\r\n" + "						</div>\r\n" + "					</form>\r\n"
				+ "				</div>\r\n" + "				<div class=\"modal-footer\">\r\n"
				+ "					<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n"
				+ "					<button onclick=\"updateUserFormSubmint()\" type=\"button\" class=\"btn btn-primary\">编辑用户</button>\r\n"
				+ "				</div>";
	}

	/**
	 * 
	 * @return
	 */
	@PostMapping(value="/admin/updateUser.html")
	public String updateUser(User user, Integer[] roleIds) {
		
		userService.updateUser(user, roleIds);
		System.out.println("用户对象信息：" + user);
		System.out.println("拿到的角色id是：" + roleIds);
		return "redirect:/admin/userNanager.html";
	}

	@ResponseBody
	@GetMapping("/test")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public List<User> testUser() {
		return userService.selectAll();

	}

}
