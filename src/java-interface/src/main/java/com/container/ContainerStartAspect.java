package com.container;


import java.util.Map;

/**
 * 容器启动之前切面
 * @author Administrator
 *
 */
public interface ContainerStartAspect {
	/**
	 * 容器启动之前
	 */
	void run(Map<String,Object> map) ;
	
}
