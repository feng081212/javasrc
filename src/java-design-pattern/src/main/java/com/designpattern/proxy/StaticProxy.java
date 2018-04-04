package com.designpattern.proxy;

public class StaticProxy implements DBDao {

	private DBDao dbDao ;
	
	public StaticProxy(DBDao dbDao){
		this.dbDao = dbDao ;
	}
	
	@Override
	public int insert(String sql) {
		System.out.println(dbDao + "(insert)代理");
		int obj = dbDao.insert(sql) ;
		System.out.println("返回结果是：" + obj);
		return obj ;
	}

	@Override
	public int update(String sql) {
		System.out.println(dbDao + "(update)代理");
		int obj = dbDao.insert(sql) ;
		System.out.println("返回结果是：" + obj);
		return obj ;
	}

	@Override
	public int select(String sql) {
		System.out.println(dbDao + "(select)代理");
		int obj = dbDao.insert(sql) ;
		System.out.println("返回结果是：" + obj);
		return obj ;
	}

	@Override
	public int delete(String sql) {
		System.out.println(dbDao + "(delete)代理");
		int obj = dbDao.insert(sql) ;
		System.out.println("返回结果是：" + obj);
		return obj ;
	}

	public static void main(String[] args) {
		DBDao dbDao = new DBDaoImpl() ;
		StaticProxy dbDaoProxy = new StaticProxy(dbDao) ;
		System.out.println(dbDaoProxy);
		dbDaoProxy.insert("insert") ;
		dbDaoProxy.update("update") ;
		dbDaoProxy.select("select") ;
		dbDaoProxy.delete("delete") ;
	}
}
