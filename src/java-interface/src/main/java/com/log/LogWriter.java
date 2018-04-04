package com.log;

public interface LogWriter {

	void info(Object object) ;
	
	void info(String name, Object object) ;
	
	void error(Object object) ;
	
	void error(String name, Object object) ;
	
	void debug(Object object) ;
	
	void debug(String name, Object object) ;
}
