package com.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
	
	public Object newInstanceProxy(Object object){
		return Proxy.newProxyInstance(
				object.getClass().getClassLoader(), 
				object.getClass().getInterfaces(), 
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println(proxy.getClass().getName());
						System.out.println(method);
						System.out.println(args);
						Object obj = method.invoke(object, args) ;
						System.out.println("返回结果是：" + obj);
						return obj;
					}
				}) ;
	}
	
	public static void main(String[] args) {
		DBDao dbDao = new DBDaoImpl() ;
		DynamicProxy dynamicProxy = new DynamicProxy() ;
		System.out.println(dynamicProxy);
		DBDao dbDaoProxy = (DBDao) dynamicProxy.newInstanceProxy(dbDao) ;
		dbDaoProxy.insert("insert") ;
		dbDaoProxy.update("update") ;
		dbDaoProxy.select("select") ;
		dbDaoProxy.delete("delete") ;
	}
}
