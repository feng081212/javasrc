package com.random;

import java.util.UUID;

public class UuId {
	/**
	 * 获取UUID唯一标识码（32位）
	 * @return
	 */
	public static final String getUUID(){
		return UUID.randomUUID().toString().trim().replaceAll("-", "") ;
	}
	
	public static void main(String[] args) {
		System.out.println(UuId.getUUID()) ;
	}
}
