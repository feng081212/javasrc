package com.secret;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.number.BinaryHex;

public class DES {
	
	private static final byte[] IV1 = { (byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
	private static final String PADDING = "DES/CBC/PKCS5Padding";
	private static final String MODE = "DES";
	
	public static final DES getInstance(){
		return new DES() ;
	}

	public String encrypt(byte[] content,String desKey) {
		try {
			IvParameterSpec iv = new IvParameterSpec(IV1);
			DESKeySpec dks = new DESKeySpec(desKey.getBytes("UTF-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MODE);
			SecretKey key = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte encryptedData[] = cipher.doFinal(content);
			return BinaryHex.byte2hex(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public byte[] decrypt(String contentHexString,String desKey){
		try {
			byte[] bytes = BinaryHex.hex2byte(contentHexString);
			IvParameterSpec iv = new IvParameterSpec(IV1);
			DESKeySpec dks = new DESKeySpec(desKey.getBytes("UTF-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MODE);
			SecretKey key = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(PADDING);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String key = "0c7fcfd2eada59bc44092675b3e8c495" ;
		String content = "{\"position\":\"经理\",\"user_name\":\"jinjianfeng\",\"phone\":\"13735884711\",\"visiting_card\":\"sKLA79TGo7pmcmVlemVybzIxDQrD3MLro7p3YW5neWlkaHFoYjEyMzQ1DQoNCruqsbEzDQrKtcD9SUQ6aS04dmJpOXZkcndud2YybXcyZDZqaQ0KyrXA/cP7s8Y6aVp3bndmMm13MmQ2amlaIA0KuavN+ElQo7o0Ny45Mi4xNTMuMTQ4DQrE2s34SVCjujE3Mi4yNi4xNjMuMjQ5DQoNCmFkbWluaXN0cmF0b3INCiExQDIjMyQ0JTVhZG1pbg==\",\"role_id\":\"1\",\"weixin\":\"13735884711\",\"email\":\"553547297@qq.com\",\"password\":\"123456\",\"qq\":\"553547297\",\"fullname\":\"金剑锋\"}" ;
		System.out.println("加密之前：" + content);
		String str = DES.getInstance().encrypt(content.getBytes("UTF-8"), key);
		System.out.println("加密之后：" + str);
		byte[] bytes = DES.getInstance().decrypt(str, key) ;
		System.out.println("加密之后：" + new String(bytes,"UTF-8"));
	}
}
