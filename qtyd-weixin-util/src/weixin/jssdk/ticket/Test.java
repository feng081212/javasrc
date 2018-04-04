package weixin.jssdk.ticket;

import java.util.HashMap;

public class Test {
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String,String>() ;
		map.put("name", "jinjianfeng") ;
		change(map);
		System.out.println(map);
		Bean bean = new Bean() ;
		change(bean);
		System.out.println(bean);
	}
	
	public static void change(HashMap<String, String> map){
		map.put("key", "name") ;
	}
	
	public static void change(Bean bean){
		bean.setName("name");
	}
}
