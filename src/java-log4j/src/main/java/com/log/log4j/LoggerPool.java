package com.log.log4j;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.NullEnumeration;

import com.pool.AbstractPool;

public class LoggerPool extends AbstractPool<String, List<Logger>> {
	
	private static LoggerPool POOL = null ;
	
	public static final LoggerPool getPool(){
		if(POOL == null){
			POOL = new LoggerPool() ;
		}
		return POOL ;
	}
	
	@Override
	public List<Logger> newInstance(String k) {
		List<Logger> list = new ArrayList<Logger>() ;
		Enumeration<?> enumeration = LogManager.getLoggerRepository().getCurrentLoggers() ;
		while(enumeration.hasMoreElements()){
			Logger logger = (Logger) enumeration.nextElement() ;
			if(logger.getAllAppenders() instanceof NullEnumeration ){
				continue ;
			}
			list.add(logger) ;
		}
		return list;
	}
	
	@Override
	public int maxLifeTime() {
		return 5 * 60 * 1000 ;
	}
}
