package cn.ybzy.qihangkt;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QihangTest {
	
	@Test
	public void test() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource.getClass());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
