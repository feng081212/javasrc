package com.log.log4j;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;

/**
 * log4j 初始化
 * @author Administrator
 */
public class Log4jInit {
	
	public final static String PROPERTIES_LOG_DIR = "logdir" ;
	
	protected static String LOG4J_PROPERTIES_DIR = "" ;
	protected static String LOG4J_DEFAULT_OUT_RESOURCE = "DEFAULT" ;
	protected static Map<String,String> LOG4J_CONFIG = new HashMap<String,String>() ;
	
	/** 设置log4j.properties的配置路径 */
	public static final void setLog4jPropertiesDir(String log4jPropertiesDir){
		LOG4J_PROPERTIES_DIR = log4jPropertiesDir ;
	}
	
	/** 设置log4j的默认输出源 */
	public static final void setLog4jDefaultOutResource(String defaultOutResource){
		LOG4J_DEFAULT_OUT_RESOURCE = defaultOutResource ;
	}
	
	/** 设置log4j.properties的变量【${key}】，此变量将配置为System.setProperty(key, value) */
	public static final void setLog4jSystemParam(String key,String value){
		if(key == null || key.trim().length() == 0 || value == null || value.trim().length() == 0)
			return ;
		LOG4J_CONFIG.put(key, value) ;
	}
	
	/** 设置log4j.properties的变量【${key}】，此变量将配置为System.setProperty(key, value) */
	public static final void setLog4jSystemParam(Map<String,String> values){
		if(values == null)
			return ;
		LOG4J_CONFIG.putAll(values);
	}
	
	/**
	 * 重新加载log4j.properties文件
	 * @throws Exception 
	 */
	public static final void loadProperty() throws Exception {
		
		if(Log4jInit.LOG4J_PROPERTIES_DIR == null || Log4jInit.LOG4J_PROPERTIES_DIR.trim().length() == 0){
			Log4jInit.LOG4J_PROPERTIES_DIR = System.getProperty(PROPERTIES_LOG_DIR) ;
		}
		if(Log4jInit.LOG4J_PROPERTIES_DIR == null || Log4jInit.LOG4J_PROPERTIES_DIR.trim().length() == 0){
			LOG4J_PROPERTIES_DIR = Log4jInit.class.getResource("/") .getPath() + "log4j.properties" ;
			if(!new File(Log4jInit.LOG4J_PROPERTIES_DIR).exists()){
				Enumeration<URL> urls = Log4jInit.class.getClassLoader().getResources("log4j.properties") ;
				while(urls.hasMoreElements()){
					URL url = urls.nextElement() ;
					if(url.getProtocol().equalsIgnoreCase("jar"))
						continue ;
					LOG4J_PROPERTIES_DIR = url.getFile() ;
					break ;
				}
			}
		}
		System.out.println("log4j.properties配置文件路径：" + Log4jInit.LOG4J_PROPERTIES_DIR);
		
		for(String key : LOG4J_CONFIG.keySet()){
			System.setProperty(key, LOG4J_CONFIG.get(key)) ;
		}
		System.out.println("log4j.properties配置文件中参数配置信息：" + LOG4J_CONFIG);
		System.out.println("log4j默认输出源：" + LOG4J_DEFAULT_OUT_RESOURCE);
		
		if(new File(Log4jInit.LOG4J_PROPERTIES_DIR).exists()){
			PropertyConfigurator.configureAndWatch(Log4jInit.LOG4J_PROPERTIES_DIR,60000);
		}else{
			System.out.println("log4j.properties配置文件路径：" + Log4jInit.LOG4J_PROPERTIES_DIR + " --- 文件不存在");
		}
	}
	
	public static void main(String[] args) {
		try {
			Log4jInit.loadProperty();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
