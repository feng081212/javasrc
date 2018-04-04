package com.spring.db.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDBDaoTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:*.xml") ;
		System.out.println(context);
		SpringDBDao dbDao = (SpringDBDao) context.getBean("springDBDao") ;
		System.out.println(dbDao);
		System.out.println(dbDao.queryForObject("select user_id from dw_user where username = 13735884711",Long.class)) ;
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println(dbDao.query("select * from dw_user where username = ?","13735884711")) ;
	}
}
