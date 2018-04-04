package com.log.log4j;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.log.LogWriter;

public class Log4jSPITest {

	public static void main(String[] args) {
		//LogFactory.info("111");  
		ServiceLoader<LogWriter> serviceLoader = ServiceLoader.load(LogWriter.class);
		Iterator<LogWriter> iterator = serviceLoader.iterator();  
        while (iterator.hasNext()) {  
        	System.out.println(iterator.next());
        }
	}
}
