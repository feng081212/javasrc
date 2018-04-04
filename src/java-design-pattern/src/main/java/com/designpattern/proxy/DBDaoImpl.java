package com.designpattern.proxy;

public class DBDaoImpl implements DBDao {

	@Override
	public int insert(String sql) {
		System.out.println(this + "(insert):" + sql);
		return 1;
	}

	@Override
	public int update(String sql) {
		System.out.println(this + "(update):" + sql);
		return 2;
	}

	@Override
	public int select(String sql) {
		System.out.println(this + "(select):" + sql);
		return 3;
	}

	@Override
	public int delete(String sql) {
		System.out.println(this + "(delete):" + sql);
		return 4;
	}

}
