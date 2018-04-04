package com.urlconnection;

import java.util.HashMap;
import java.util.Map;

import com.http.urlconnection.HttpURLConnectionSender;



public class HttpURLConnectionTest {

	public static void main(String[] args) throws Exception {
		Map<String,Object> head = new HashMap<String, Object>() ;
		byte[] bytes = new HttpURLConnectionSender("https://www.tianyancha.com",head).get();
		System.out.println(new String(bytes,"UTF-8"));
	}
}
