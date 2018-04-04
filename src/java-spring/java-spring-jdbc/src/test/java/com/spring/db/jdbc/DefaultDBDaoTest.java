package com.spring.db.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.db.jdbc.DefaultDBDao;

public class DefaultDBDaoTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:*.xml") ;
		System.out.println(context);
		DefaultDBDao dbDao = (DefaultDBDao) context.getBean("dbDao") ;
		System.out.println(dbDao);
		System.out.println(dbDao.query("select * from dw_user limit 1")) ;
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println(dbDao.query("select * from dw_user where username = ?","13735884711")) ;
	}
}
