package com.db.jdbc;

public class DefaultDBDaoTest {

	public static void main(String[] args) {
		DefaultDBDao dbDao = new DefaultDBDao() ;
	
		String sql = "insert into `user` (username,password,age)values(?,?,?)" ;
		System.out.println(dbDao.insertOfReturnId(sql,"jinjianfeng2","123456","29")) ;
		
		sql = "select * from `user` order by id desc limit 2" ;
		System.out.println(dbDao.query(sql)) ;
		
		sql = "select * from `user` where username = ?" ;
		System.out.println(dbDao.query(sql,"jinjianfeng")) ;
	}
}
