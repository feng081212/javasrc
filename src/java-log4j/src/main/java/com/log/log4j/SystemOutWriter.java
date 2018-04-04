package com.log.log4j;

import java.lang.management.ManagementFactory;

import com.log.LogFactory;
import com.log.LogWriter;

public class SystemOutWriter implements LogWriter {

	private static String PID = "" ;

	public SystemOutWriter(){
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
		System.out.println(getPrintString(object));
	}
	
	@Override
	public void debug(Object object){
		debug(Log4jInit.LOG4J_DEFAULT_OUT_RESOURCE,object);
	}
	
	@Override
	public void debug(String name , Object object){
		System.out.println(getPrintString(object));
	}
	
	@Override
	public void error(Object object){
		error(Log4jInit.LOG4J_DEFAULT_OUT_RESOURCE,object);
	}
	
	@Override
	public void error(String name, Object object){
		System.out.println(getPrintString(object));
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
			if(stackTraceElement.getClassName().equalsIgnoreCase(SystemOutWriter.class.getName())){
				continue ;
			}
			break ;
		}
		return stackTraceElement ;
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
