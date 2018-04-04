package com.designpattern.proxy;

public interface DBDao {

	int insert(String sql);
	
	int update(String sql);
	
	int select(String sql);
	
	int delete(String sql);
}
