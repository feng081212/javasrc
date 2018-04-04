package com.container;


import java.util.Map;

/**
 * 容器启动完成切面
 * @author Administrator
 *
 */
public interface ContainerStartedAspect {
	/**
	 * 容器启动之后
	 */
	void run(Map<String,Object> map) ;
	
}
