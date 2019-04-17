package com.yh.jdbc.test;

import java.util.List;

import org.junit.Test;
import com.yh.jdbc.dao.impl.StudentDaoImpl;
import com.yh.jdbc.dao.IStudentDao;
import com.yh.jdbc.daomain.Student;

public class StudentDaoTest {

	@Test
	public void save() {
		// 创建一个学生对象
		Student stu = new Student();
		// 给属性赋值
		stu.setName("雅雅28");
		stu.setAge(12);
		// 把学生保存到数据库中
		IStudentDao dao = new StudentDaoImpl();
		dao.save(stu);
	}

	@Test
	public void update() {
		Student stu = new Student();
		stu.setAge(260);
		stu.setId(7);
		IStudentDao dao = new StudentDaoImpl();
		dao.update(stu);
	}

	@Test
	public void delete() {
		IStudentDao dao = new StudentDaoImpl();
		dao.delete(1);
	}

	@Test
	public void get() {
		IStudentDao dao = new StudentDaoImpl();
		Student stu = dao.get(3);
		// 输出查找的数据
		System.out.println(stu);
	}

	@Test
	public void getAll() {
		IStudentDao dao = new StudentDaoImpl();
		List<Student> allstu = dao.getALL();
		System.out.println(allstu);
	}

	@Test
	public void getCount() {
		IStudentDao dao = new StudentDaoImpl();
		System.out.println(dao.getCount());
	}

}
