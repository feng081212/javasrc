package com.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bean.User;
import com.mybatis.MyBatisFactory;

public class UserDao {

	public void selectOne(){
		SqlSession sqlSession = MyBatisFactory.sqlSessionFactory.openSession() ;
		System.out.println(sqlSession.selectOne("com.mybatis.dao.UserDao.selectsql","jinjianfeng")) ;
	}
	
	public void selectList(){
		SqlSession sqlSession = MyBatisFactory.sqlSessionFactory.openSession() ;
		System.out.println(sqlSession.selectList("com.mybatis.dao.UserDao.selectsql","13735884711")) ;
	}
	
	public void selectResultBean(){
		SqlSession sqlSession = MyBatisFactory.sqlSessionFactory.openSession() ;
		System.out.println(sqlSession.selectOne("com.mybatis.dao.UserDao.selectReturnBean","13735884711")) ;
	}
	
	public void insertOne(){
		User user = new User() ;
		user.setUsername("13735884711");
		user.setPassword("123456");
		SqlSession sqlSession = MyBatisFactory.sqlSessionFactory.openSession(true) ;
		System.out.println(sqlSession.update("com.mybatis.dao.UserDao.insertOne",user)) ;
	}
	
	public void insertOne2(){
		User user = new User() ;
		user.setUsername("13735884711");
		user.setPassword("123456");
		SqlSession sqlSession = MyBatisFactory.sqlSessionFactory.openSession() ;
		System.out.println(sqlSession.update("com.mybatis.dao.UserDao.insertOne",user)) ;
		sqlSession.commit();
	}
	
	public void insertList(){
		User user = new User() ;
		user.setUsername("13735884711");
		user.setPassword("123456");
		List<User> list = new ArrayList<User>() ;
		list.add(user);
		list.add(user);
		list.add(user);
		SqlSession sqlSession = MyBatisFactory.sqlSessionFactory.openSession(true) ;
		System.out.println(sqlSession.update("com.mybatis.dao.UserDao.insertList",list)) ;
	}
	
	public static void main(String[] args) {
//		new UserDao().selectOne();
//		new UserDao().selectList();
//		new UserDao().selectResultBean();
//		new UserDao().insertOne();
//		new UserDao().insertOne2();
		new UserDao().insertList();
	}
}
