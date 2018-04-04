package com.redis.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pool.Comparer;

public abstract class AbstractDBRedisPool extends AbstractRedisHashPool<String,Map<String,Object>> {
	
	@Override
	public void init(){
		try {
			List<Map<String,Object>> list = newInstanceList() ;
			if(list == null || list.isEmpty()){
				return ;
			}
			for(Map<String,Object> map : list){
				add(key(map), map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract List<Map<String,Object>> newInstanceList() ;
	
	public abstract String key(Map<String,Object> item) ;
	
	public List<Map<String,Object>> list(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>() ;
		List<String> vallist = values() ;
		for(String val : vallist){
			list.add(restoreValue(val)) ;
		}
		return list ;
	}
	
	public List<Map<String,Object>> list(Comparer<Map<String,Object>> comparer){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>() ;
		List<String> vallist = values() ;
		for(String val : vallist){
			Map<String,Object> value = restoreValue(val) ;
			if(comparer.comparer(value, null)){
				list.add(value) ;
			}
		}
		return list ;
	}
	
	@Override
	protected String stringField(String field) {
		return field;
	}

	@Override
	protected String restoreField(String str) {
		return str;
	}
}
