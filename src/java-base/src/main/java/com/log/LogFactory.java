package com.log;

import java.util.ArrayList;
import java.util.List;

import com.spi.SpiFactory;

public class LogFactory {
	
	private static List<LogWriter> LOG_WRITER_LIST = null ;

	public void setLogger(LogWriter logger) {
		if(LOG_WRITER_LIST == null){
			LOG_WRITER_LIST = new ArrayList<LogWriter>() ;
		}
		LOG_WRITER_LIST.add(logger) ;
	}
	
	public static List<LogWriter> getLogger(){
		if(LOG_WRITER_LIST == null){
			LOG_WRITER_LIST = SpiFactory.getList(LogWriter.class) ;
		}
        return LOG_WRITER_LIST ;
	}

	public static final void info(Object object) {
		if(getLogger() == null){
			System.out.println(object) ;
		} else {
			for (LogWriter writer : LOG_WRITER_LIST) {
				writer.info(object);
			}
		}
	}

	public static final void info(String name, Object object) {
		if(getLogger() == null){
			System.out.println(object) ;
		} else {
			for (LogWriter writer : LOG_WRITER_LIST) {
				writer.info(name,object);
			}
		}
	}

	public static final void error(Object object) {
		if(getLogger() == null){
			System.out.println(object) ;
		} else {
			for (LogWriter writer : LOG_WRITER_LIST) {
				writer.error(object);
			}
		}
	}

	public static final void error(String name, Object object) {
		if(getLogger() == null){
			System.out.println(object) ;
		} else {
			for (LogWriter writer : LOG_WRITER_LIST) {
				writer.error(name,object);
			}
		}
	}

	public static final void debug(Object object) {
		if(getLogger() == null){
			System.out.println(object) ;
		} else {
			for (LogWriter writer : LOG_WRITER_LIST) {
				writer.debug(object);
			}
		}
	}

	public static final void debug(String name, Object object) {
		if(getLogger() == null){
			System.out.println(object) ;
		} else {
			for (LogWriter writer : LOG_WRITER_LIST) {
				writer.debug(name,object);
			}
		}
	}
}
