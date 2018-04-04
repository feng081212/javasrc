package com.spring.db.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.log.LogFactory;
import com.spi.SpiFactory;
import com.spring.context.SpringContext;

public class DefaultSpringAtomicDBDao implements SpringAtomicDBDao<Map<String,Object>> {

	protected JdbcTemplate jdbcTemplate = null ;
	
	protected DataSource dataSource = null ;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource ;
	}

	@Override
	public DataSource getDataSource() {
		if(this.dataSource == null){
			this.dataSource = (DataSource) SpringContext.getBean("dataSource") ;
		}
		if(this.dataSource == null){
			this.dataSource = SpiFactory.get(DataSource.class) ;
		}
		return this.dataSource ;
	}
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		if(this.jdbcTemplate == null){
			this.jdbcTemplate = new JdbcTemplate(getDataSource()) ;
		}
		return this.jdbcTemplate ;
	}
	
	@Override
	public int update(String sql) {
		LogFactory.info(sql);
		return getJdbcTemplate().update(sql) ;
	}

	@Override
	public int update(String sql, Object... values) {
		String sqlLog = sql ;
		for (Object object : values) {
			sqlLog = sqlLog.replace("?", object.toString()) ;
		}
		LogFactory.info(sqlLog);
		return getJdbcTemplate().update(sql,values) ;
	}

	@Override
	public int[] updateBatch(String... sql) {
		for (String str : sql) {
			LogFactory.info(str);
		}
		return getJdbcTemplate().batchUpdate(sql) ;
	}

	@Override
	public int[] updateBatch(String sql, List<Object[]> data) {
		for (Object[] values : data) {
			String sqlLog = sql ;
			for (Object object : values) {
				sqlLog = sqlLog.replace("?", object.toString()) ;
			}
			LogFactory.info(sqlLog);
		}
		return getJdbcTemplate().batchUpdate(sql) ;
	}

	@Override
	public List<Map<String, Object>> query(String sql) {
		LogFactory.info(sql);
		return getJdbcTemplate().queryForList(sql) ;
	}

	@Override
	public List<Map<String, Object>> query(String sql, Object... values) {
		String sqlLog = sql ;
		for (Object object : values) {
			sqlLog = sqlLog.replace("?", object.toString()) ;
		}
		LogFactory.info(sqlLog);
		return getJdbcTemplate().queryForList(sql,values) ;
	}

	@Override
	public long insertOfReturnId(String sql) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(sql, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	@Override
	public long insertOfReturnId(String sql, Object... values) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new CustomPreparedStatementCreator(sql.toString(),values), keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	@Override
	public <E> E queryForObject(String sql, Class<E> cls) {
		LogFactory.info(sql);
		return getJdbcTemplate().queryForObject(sql, cls) ;
	}

	@Override
	public <E> E queryForObject(String sql, Class<E> cls, Object... values) {
		String sqlLog = sql ;
		for (Object object : values) {
			sqlLog = sqlLog.replace("?", object.toString()) ;
		}
		LogFactory.info(sqlLog);
		return getJdbcTemplate().queryForObject(sql, cls,values) ;
	}

	@Override
	public <E> E queryForObject(String tableName, Class<E> cls, Map<String, Object> where) {
		StringBuffer sql = new StringBuffer("select * from ") ;
		sql.append(tableName + " where ");
		List<Object> values = new ArrayList<Object>();
		for (String key : where.keySet()) {
			sql.append(key + " = ? , ");
			values.add(where.get(key)) ;
		}
		sql.delete(sql.length() - 2, sql.length()) ;
		return queryForObject(sql.toString(),cls,values.toArray()) ;
	}
}
