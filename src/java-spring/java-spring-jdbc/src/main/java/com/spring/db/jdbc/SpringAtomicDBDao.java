package com.spring.db.jdbc;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.db.jdbc.AtomicDBDao;

public interface SpringAtomicDBDao<T> extends AtomicDBDao<T> {

	JdbcTemplate getJdbcTemplate() ;
	
	/**
	 * 查询，返回指定类型的结果
	 * @param sql SQL语句
	 * @param cls 指定类型
	 * @return
	 */
	<E> E queryForObject(String sql, Class<E> cls) ;
	
	/**
	 * 查询，返回指定类型的结果 
	 * @param sql SQL语句
	 * @param cls 指定类型
	 * @param params SQL语句中?对应的值
	 * @return
	 */
	<E> E queryForObject(String sql, Class<E> cls, Object... params) ;
	
	/**
	 * 查询，返回指定类型的结果
	 * @param tableName 表名
	 * @param cls 指定类型
	 * @param where where语句中的字段名已经字段名对应的值
	 * @return
	 */
	<E> E queryForObject(String tableName, Class<E> cls, Map<String,Object> where) ;
}
