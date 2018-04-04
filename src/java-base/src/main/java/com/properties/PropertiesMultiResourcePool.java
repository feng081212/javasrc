package com.properties;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.pool.AbstractPool;

public class PropertiesMultiResourcePool extends AbstractPool<String, Map<String,Object>> {

	private final static PropertiesMultiResourcePool POOL = new PropertiesMultiResourcePool() ;
	
	public final static Map<String,Object> getProperties(String fileName){
		return POOL.get(fileName) ;
	}
	
	public final static Object getValue(String fileName, String key){
		return getProperties(fileName).get(key) ;
	}
	
	@Override
	public Map<String, Object> newInstance(String k) {
		try {
			Enumeration<URL> enumeration = Thread.currentThread().getContextClassLoader().getResources(k) ;
			Map<String,Object> value = new HashMap<String, Object>() ;
			while(enumeration.hasMoreElements()) {
	            InputStream inputStream = null;
	            try {
	            	inputStream = enumeration.nextElement().openStream() ;
	                Properties properties = new Properties();
	                properties.load(inputStream);
	                for(Object key : properties.keySet()){
	                	if(!value.containsKey(key)){
	                		value.put(key+"", properties.getProperty(key+"")) ;
	                	}
	                }
	            } finally {
	            	inputStream.close();
	            }
	        }
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}

}
