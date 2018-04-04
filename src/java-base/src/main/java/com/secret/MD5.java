package com.secret;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import com.number.BinaryHex;

public class MD5 {

	public static final String ALGORITHM = "MD5";
	
	public static final MD5 getIntacne(){
		return new MD5() ;
	}
	
	/**
     * 签名byte数组
     * @param srcBytes 需要签名的byte数组
     * @return 签名结果
     */
	public byte[] encode(byte[] srcBytes) {
		return encode(new ByteArrayInputStream(srcBytes)) ;
	}
	
	/**
     * 签名InputStream
     * @param is 需要签名的InputStream
     * @return 签名结果
     */
	public byte[] encode(InputStream is){
		try {
			if(is == null){
				return null ;
			}
			BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			byte[] buffer = new byte[1024];
	        int i = 0;
	        while((i = bufferedInputStream.read(buffer)) > 0){
	       		md.update(buffer, 0, i);
	        }
	        return md.digest() ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}        
    }
	
	public static void main(String[] args) {
		System.out.println(BinaryHex.byte2hex(MD5.getIntacne().encode("!1@2#3$4%5".getBytes())));
	}

}
