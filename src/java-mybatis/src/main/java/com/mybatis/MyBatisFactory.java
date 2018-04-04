package com.mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisFactory {

	public final static SqlSessionFactory sqlSessionFactory = load() ;
	
	public final static SqlSessionFactory load() {
		try{
			if(sqlSessionFactory != null){
				return sqlSessionFactory ;
			}
			String resource = "mybatis.xml";  
	        // 得到配置文件流  
	        InputStream inputStream = Resources.getResourceAsStream(resource);  
	        // 创建会话工厂，传入mybatis的配置文件信息  
	        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);  
	        return sqlSessionFactory;
		}catch(Exception e){
			return null ;
		}
	}
}
