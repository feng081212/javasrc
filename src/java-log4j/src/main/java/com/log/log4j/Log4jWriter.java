package com.log.log4j;

import java.lang.management.ManagementFactory;
import java.util.List;

import org.apache.log4j.Logger;

import com.log.LogFactory;
import com.log.LogWriter;

public class Log4jWriter implements LogWriter {

	private static String PID = "" ;
	
	public Log4jWriter(){
		System.out.println("Log4jWriter new instance");
		if(PID == null || PID.equals("") ){
			PID = getPid() ;
		}
	}
	
	@Override
	public void info(Object object){
		info(Log4jInit.LOG4J_DEFAULT_OUT_RESOURCE,object);
	}
	
	@Override
	public void info(String name , Object object){
		getLogger(name).info(getPrintString(object));
	}
	
	@Override
	public void debug(Object object){
		debug(Log4jInit.LOG4J_DEFAULT_OUT_RESOURCE,object);
	}
	
	@Override
	public void debug(String name , Object object){
		getLogger(name).debug(getPrintString(object));
	}
	
	@Override
	public void error(Object object){
		error(Log4jInit.LOG4J_DEFAULT_OUT_RESOURCE,object);
	}
	
	@Override
	public void error(String name, Object object){
		getLogger(name).error(getPrintString(object));
	}
	
	public Object getPrintString(Object value){
		if(value instanceof Exception){
			return value ;
		}
		StringBuffer buffer = new StringBuffer() ;
		StackTraceElement stackTraceElement = getStackTraceElement4Log() ;
		buffer.append("[PID=").append(PID).append("]") ;
		buffer.append(stackTraceElement.getClassName()).append("[").append(stackTraceElement.getLineNumber()).append("]") ;
		buffer.append(" - ").append(value == null ? "" : value.toString()) ;
		return buffer.toString() ;
	}
	
	public StackTraceElement getStackTraceElement4Log(){
		Throwable throwable = new Throwable() ;
		StackTraceElement stackTraceElement = null ;
		int index = 0 ;
		StackTraceElement[] stackTraceElements = throwable.getStackTrace() ;
		while(index < stackTraceElements.length){
			stackTraceElement = stackTraceElements[index ++] ;
			if(stackTraceElement.getClassName().equalsIgnoreCase(LogFactory.class.getName())){
				continue ;
			}
			if(stackTraceElement.getClassName().equalsIgnoreCase(Log4jWriter.class.getName())){
				continue ;
			}
			break ;
		}
		return stackTraceElement ;
	}
	
	protected Logger getLogger(String name){
		StackTraceElement stackTraceElement = getStackTraceElement4Log() ;
		List<Logger> list = LoggerPool.getPool().get("ALL") ;
		for(Logger logger : list){
			String classname = stackTraceElement.getClassName() ;
			if(classname.startsWith(logger.getName())){
				return logger ;
			}
		}
		for(Logger logger : list){
			if(name.equalsIgnoreCase(logger.getName())){
				return logger ;
			}
		}
		return null ;
	} 
	
	public static final String getPid(){
		String pid = ManagementFactory.getRuntimeMXBean().getName();
	    int indexOf = pid.indexOf('@');  
	    if (indexOf > 0){  
	        pid = pid.substring(0, indexOf);  
	    }
	    return pid ;
	}
}
