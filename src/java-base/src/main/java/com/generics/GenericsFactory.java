package com.generics;

import java.lang.reflect.ParameterizedType;



public class GenericsFactory<A> {

	public Object obj = null ;
	
	public void run(Object object) throws Exception{
		this.obj = object ;
		System.out.println((ParameterizedType)this.getClass().getDeclaredField("obj").getGenericType()) ;
	}
	
	public static void main(String[] args) throws Exception {
		GenericsFactory<String> g = new GenericsFactory<String>() ;
		g.run(g);
	}
}
