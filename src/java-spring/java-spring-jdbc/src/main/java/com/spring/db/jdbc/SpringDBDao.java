package com.spring.db.jdbc;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.db.jdbc.DefaultDBDao;

public class SpringDBDao extends DefaultDBDao implements SpringAtomicDBDao<Map<String,Object>> {

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return ((SpringAtomicDBDao<Map<String,Object>>)getAtomicDBDao()).getJdbcTemplate() ;
	}

	@Override
	public <E> E queryForObject(String sql, Class<E> cls) {
		return ((SpringAtomicDBDao<Map<String,Object>>)getAtomicDBDao()).queryForObject(sql, cls);
	}

	@Override
	public <E> E queryForObject(String sql, Class<E> cls, Object... params) {
		return ((SpringAtomicDBDao<Map<String,Object>>)getAtomicDBDao()).queryForObject(sql, cls, params);
	}

	@Override
	public <E> E queryForObject(String tableName, Class<E> cls, Map<String, Object> where) {
		return ((SpringAtomicDBDao<Map<String,Object>>)getAtomicDBDao()).queryForObject(tableName, cls, where);
	}

}
