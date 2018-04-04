package com.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.db.mybatis.mapper.UserMapper;
import com.db.mybatis.pojo.User;
import com.mybatis.MyBatisFactory;

public class MybatisTest {

	private SqlSession session ;
	
	@Before
	public void init(){
		session = MyBatisFactory.sqlSessionFactory.openSession() ;
	}
	
	@After
	public void destory(){
		session.commit();
		session.close();
		session = null ;
	}
	
	@Test
	public void test1(){
		User user = new User() ;
		user.setUsername("13735884711");
		user.setPassword("123456");
		user.setAge(20);
		UserMapper userMapper = session.getMapper(UserMapper.class) ;
		userMapper.insert(user) ;
	}
	
	@Test
	public void test2(){
		UserMapper userMapper = session.getMapper(UserMapper.class) ;
		System.out.println(userMapper.selectByPrimaryKey(56)) ;
	}
}
