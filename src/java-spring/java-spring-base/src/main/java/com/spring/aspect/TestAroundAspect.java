package com.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TestAroundAspect {
	
	@Around("execution(@com.spring.annotation.TestAnnotation * *(..))")
	public Object doCache(ProceedingJoinPoint pjpParam) throws Throwable {
		Signature sig = pjpParam.getSignature();
		System.out.println(sig) ;
		Object[] args = pjpParam.getArgs();
		for(Object arg : args){
			System.out.println(arg) ;
		}
		Object result =  pjpParam.proceed(pjpParam.getArgs());
		System.out.println(result) ;
		return result ;
	}
}
