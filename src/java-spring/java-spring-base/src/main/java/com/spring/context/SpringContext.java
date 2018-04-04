package com.spring.context;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;

import com.container.ContainerStartedAspect;


/**
 * Spring 上下文
 */

public class SpringContext implements ApplicationContextAware , ApplicationListener<ApplicationContextEvent> {
		
    private static ApplicationContext applicationContext = null;
    private static boolean RUN = false ;
    
    /** 
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境 
     * @param applicationContext 
     */  
    public void setApplicationContext(ApplicationContext applicationContext) {  
    	SpringContext.applicationContext = applicationContext;  
    }
    /** 
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext() { 
        return applicationContext;  
    }
    
    /** 
     * 获取对象 
     * @param name 
     * @return Object
     * @throws BeansException 
     */  
    public static Object getBean(String name) throws BeansException {
    	try{
	    	if(getApplicationContext() == null){
	    		return null ;
	    	}
	        return getApplicationContext().getBean(name); 
    	}catch(Exception e){
    		return null ;
    	}
    }
    
    public static <T> Map<String,T> getBean(Class<T> classes){
    	if(getApplicationContext() == null){
    		return null ;
    	}
    	return getApplicationContext().getBeansOfType(classes) ;
    }
    
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		try{
			ApplicationContext context = event.getApplicationContext() ;
			if(context.getParent() == null && !RUN){
				RUN = true ;
				Map<String,ContainerStartedAspect> map = context.getBeansOfType(ContainerStartedAspect.class) ;
				for (final ContainerStartedAspect startAfterSpringLoaded : map.values()) {
					if(startAfterSpringLoaded == null){
						continue ;
					}
					try{
						new Thread(new Runnable() {
							@Override
							public void run() {
								startAfterSpringLoaded.run(null);
							}
						}).start();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
}
