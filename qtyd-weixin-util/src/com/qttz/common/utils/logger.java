package com.qttz.common.utils;


public class logger {

	public static void write(Object object){
		if(object==null||object.toString().equalsIgnoreCase("")) 
			System.out.println(new Throwable().getStackTrace()[1].getClassName()+" - null");
		else 
			System.out.println(new Throwable().getStackTrace()[1].getClassName()+" - "+object.toString());
	}
}
