package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLoader {

	public final static ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:*.xml") ;
	
}
