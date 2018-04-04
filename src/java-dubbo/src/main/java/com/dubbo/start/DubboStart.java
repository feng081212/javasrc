package com.dubbo.start;

import java.util.Set;

import com.container.ContainerStartAspect;
import com.javaclass.JavaClassFactory;


public class DubboStart {

	public static void main(String[] args) {
		start(args);
	}
	
	public final static void start(String[] args) {
		Set<ContainerStartAspect> set = JavaClassFactory.getObjectsByInterface("", ContainerStartAspect.class) ;
		System.out.println("Dubbo启动之前先初始化：" + set) ;
		for(ContainerStartAspect dubboAspect : set ){
			try {
				dubboAspect.run(null) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		com.alibaba.dubbo.container.Main.main(args);
	}
}
