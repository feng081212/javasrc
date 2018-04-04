package com.spring.aspect;

import java.util.Set;

import com.javaclass.JavaClassFactory;
import com.spring.SpringLoader;
import com.spring.annotation.TestAnnotation;

public class TestAspect {

	@TestAnnotation
	public String run(String str1,String str2){
		return "TestAspect.run(String,String) return success" ;
	}
	
	public static void main(String[] args) {
		Set<Class<?>> set = JavaClassFactory.findClasses("com");
		for (Class<?> cls : set) {
			System.out.println(cls);
		}
		TestAspect testAspect = (TestAspect) SpringLoader.context.getBean("testAspect") ;
		testAspect.run("str1", "str2") ;
	}
}
