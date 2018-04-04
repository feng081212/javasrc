package com.log.log4j;

import com.log.LogFactory;

public class Log4jTest {

	public static void main(String[] args) {
		System.out.println("11111111");
		LogFactory.info("111");  
//		try {
//			Enumeration<URL> ps = Thread.currentThread().getContextClassLoader().getResources("log4j.properties");
//			while(ps.hasMoreElements()) {
//	            InputStream in = null;
//	            try {
//	            	URL url = ps.nextElement() ;
//	                in = url.openStream();
//	                Properties p = new Properties();
//	                p.load(in);
//	                for(Object key : p.keySet())
//	                	System.out.println(p.getProperty(key+""));
//	                System.out.println("----------------------" + url);
//	            } finally {
//	            	in.close();
//	            }
//	        }
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
