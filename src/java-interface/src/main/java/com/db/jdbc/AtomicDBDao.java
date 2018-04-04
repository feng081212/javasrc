package com.db.jdbc;

import java.util.List;

import javax.sql.DataSource;

public interface AtomicDBDao<T> {
	
	DataSource getDataSource() ;
	
	void setDataSource(DataSource dataSource) ;
	
	/**
	 * 执行更新语句（执行update、insert、alert语句）
	 * @param sql 		SQL语句
	 * @return
	 */
	int update(String sql) ;
	
	/**
	 * 执行更新语句（执行update、insert、alert语句）
	 * @param sql 		SQL语句
	 * @param values 	SQL语句中?对应的值
	 * @return
	 */
	int update(String sql, Object... values) ;
	
	/**
	 * 批量执行更新语句（执行update、insert、alert语句）
	 * @param sql
	 * @return
	 */
	int[] updateBatch(String... sql) ;
	
	/**
	 * 批量执行更新语句（执行update、insert、alert语句）
	 * @param sql 		SQL语句
	 * @param data		要插入的记录的字段名和值
	 */
	int[] updateBatch(String sql, List<Object[]> data) ;
	
	/**
	 * 查询（只执行select语句）
	 * @param sql		SQL语句
	 * @return
	 */
	List<T> query(String sql) ;
	
	/**
	 * 查询（只执行select语句）
	 * @param sql		SQL语句
	 * @param values 	SQL语句中?对应的值
	 * @return
	 */
	List<T> query(String sql,Object... values) ;
	
	/**
	 * 插入一条记录，并且返回主键ID（只执行insert语句）
	 * @param sql		SQL语句
	 * @return
	 */
	long insertOfReturnId(String sql) ;
	
	/**
	 * 插入一条记录，并且返回主键ID（只执行insert语句）
	 * @param sql		SQL语句
	 * @param values 	SQL语句中?对应的值
	 * @return
	 */
	long insertOfReturnId(String sql,Object... values) ;
}
