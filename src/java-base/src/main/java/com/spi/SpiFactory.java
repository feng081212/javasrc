package com.spi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class SpiFactory<T> {

	public static final <T> T get(Class<T> cls){
		ServiceLoader<T> serviceLoader = ServiceLoader.load(cls);
		Iterator<T> iterator = serviceLoader.iterator();  
        if (iterator.hasNext()) {  
        	return iterator.next() ;  
        }
        return null ;
	}
	
	public static final <T> List<T> getList(Class<T> cls){
		ServiceLoader<T> serviceLoader = ServiceLoader.load(cls);
		List<T> list = new ArrayList<T>() ;
		Iterator<T> iterator = serviceLoader.iterator();  
        while (iterator.hasNext()) {  
        	list.add(iterator.next()) ;  
        }
        return list ;
	}
}
