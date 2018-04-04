package com.qttz.common.utils;

import java.security.MessageDigest;

public class MD5Util {
	
	public static MD5Util getInstance(){
		return new MD5Util();
	}
	
	public String getMD5(String token){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md.digest(token.getBytes("UTF-8"));
			return ByteUtil.getInstance().ByteToHexString(md5Bytes) ;
		} catch (Exception e) {
			e.printStackTrace();
			return "" ;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.getInstance().getMD5("qttz_crm_online"));
		byte b = -5 ;
		System.out.println(Integer.toBinaryString(b));
		int bint = b ;
		System.out.println(Integer.toBinaryString(bint));
		System.out.println(Integer.toBinaryString("f".getBytes()[0]));
		System.out.println(Integer.parseInt("f", 16));
	}
}
