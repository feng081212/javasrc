package com.orm.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.db.jdbc.DefaultAtomicDBDao;
import com.orm.annotation.Column;
import com.orm.annotation.Table;

public class BeanUtil {

	public void save(Object obj){
		Class<?> clazz = obj.getClass() ;
		String tableName = clazz.getSimpleName() ;
		Table table = clazz.getAnnotation(Table.class) ;
		if (table != null){
			tableName = table.value() ;
		}
		StringBuffer sql = new StringBuffer() ;
		sql.append("INSERT INTO ").append(tableName).append("(") ;
		List<Object> paramList = new ArrayList<Object>() ;
		Field[] fields = clazz.getDeclaredFields() ;
		if(fields == null || fields.length == 0){
			return ;
		}
		for(Field field : fields){
			String fieldName = field.getName() ;
			Column column = field.getAnnotation(Column.class) ;
			if(column != null){
				fieldName = column.value() ;
			}
			field.setAccessible(true);
			try {
				Object param = field.get(obj) ;
				if(param == null){
					sql.append("null,") ;
				} else {
					sql.append("?,") ;
					paramList.add(param) ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sql.delete(sql.length()-1, sql.length()) ;
		sql.append(")") ;
		new DefaultAtomicDBDao().update(sql.toString(), paramList.toArray()) ;
	}
	
	public static void main(String[] args) {
		DefaultAtomicDBDao dbDao = new DefaultAtomicDBDao() ;
		dbDao.update("", null) ;
	}
}
