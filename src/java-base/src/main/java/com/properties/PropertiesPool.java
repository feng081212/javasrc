package com.properties;

import java.util.ResourceBundle;

import com.pool.AbstractPool;

public class PropertiesPool extends AbstractPool<String, ResourceBundle>{

	private final static PropertiesPool POOL = new PropertiesPool() ;
	
	@Override
	public ResourceBundle newInstance(String fileName) {
		return ResourceBundle.getBundle(formatFileName(fileName)) ;
	}
	
	public String formatFileName(String fileName){
		int index = fileName.indexOf(".") ;
		fileName = fileName.substring(0,index > 0 ? index : fileName.length());
		return fileName ;
	}
	
	public final static ResourceBundle getProperties(String fileName){
		return POOL.get(fileName) ;
	}
	
	public final static String getValue(String fileName, String key){
		return getProperties(fileName).getString(key) ;
	}
}
