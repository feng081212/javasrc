package com.spring.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class CustomPreparedStatementCreator implements PreparedStatementCreator {

	private String sql = "" ;
	private Object[] collection = null ;
	
	public CustomPreparedStatementCreator(String sql,Collection<Object> collection){
		this.sql = sql ;
		this.collection = collection.toArray() ;
	}
	
	public CustomPreparedStatementCreator(String sql,Object... values){
		this.sql = sql ;
		this.collection = values ;
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		int i = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		for (Object obj : collection) {
			if(obj instanceof String){
				preparedStatement.setString(++i, String.valueOf(obj));
			}else if (obj instanceof Long){
				preparedStatement.setLong(++i, Long.valueOf(obj+""));
			}else if (obj instanceof Integer){
				preparedStatement.setInt(++i, Integer.valueOf(obj+""));
			}else if (obj instanceof Double){
				preparedStatement.setDouble(++i, Double.valueOf(obj+""));
			}else{
				preparedStatement.setString(++i,String.valueOf(obj));
			}
		}
		return preparedStatement;
	}

}
