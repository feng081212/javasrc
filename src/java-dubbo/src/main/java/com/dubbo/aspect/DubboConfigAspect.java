package com.dubbo.aspect;

import java.util.Map;

import com.container.ContainerStartAspect;
import com.properties.PropertiesMultiResourcePool;

public class DubboConfigAspect implements ContainerStartAspect {

	@Override
	public void run(Map<String, Object> map) {
		Map<String,Object> dubboConfig = PropertiesMultiResourcePool.getProperties("dubbo.properties") ;
		for(String key : dubboConfig.keySet()){
			System.setProperty(key, dubboConfig.get(key)+"") ;
		}
	}
}
