package com.yh.jdbc.dao;

import java.util.List;

import com.yh.jdbc.daomain.Student;

public interface IStudentDao {
	//1.保存数据
	void save(Student stu);
	
	//2.修改指定学生的信息
	void update(Student stu);
	
	//3.删除学生
	void delete(int id);
	
	//4.获取指定的学生   
	Student get(int id);
	
	//5.获取所有的学生    
	List<Student> getALL();
	
	//6.获取学生的总数
	Integer getCount();

}
