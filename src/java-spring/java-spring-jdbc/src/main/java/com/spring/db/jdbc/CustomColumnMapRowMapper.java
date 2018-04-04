package com.spring.db.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class CustomColumnMapRowMapper extends ColumnMapRowMapper {

	public Map<String, Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Map<String, Object> values = createColumnMap(columnCount);
        for (int i = 1; i <= columnCount; i++) {
        	try{
	            String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i));
	            Object obj = null;
	            if (rsmd.getColumnTypeName(i).equals("TINYINT")) {
	                obj = resultSet.getInt(i);
	            } else {
	                obj = getColumnValue(resultSet, i);
	            }
	            values.put(key, obj);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        return values;
    }
}
