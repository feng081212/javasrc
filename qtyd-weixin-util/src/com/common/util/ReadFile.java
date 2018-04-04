package com.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import com.common.param.WeixinParameter;


public class ReadFile {
	
	public static String getString(String filename){
		String filePath = ReadFile.class.getResource("").getPath() + "/" + filename ;
		filePath = filePath.replaceAll("%20", " ");
		return ReadFile.read(filePath, WeixinParameter.weixin_charset);
	}
	
	public static void main(String[] args) {
		System.out.println(ReadFile.getString(""));
	}
	
	public static String read(String file,String charset){
		FileInputStream fis = null ;
		ByteArrayOutputStream baos = null ;
		try{
			fis = new FileInputStream(new File(file));
			baos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			while(fis.read(b)>0){
				baos.write(b);
			}
			return new String(baos.toByteArray(),charset);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fis!=null) fis.close();
				if(baos!=null) baos.close();
			}catch(Exception e){
				fis = null ;
				baos = null ;
			}
		}
		return "";
	}
	
	public static byte[] readByte(String filePath){
		FileInputStream file = null ;
		try {
			ByteArrayOutputStream bais = new ByteArrayOutputStream();
			file = new FileInputStream(new File(filePath));
			int length = 0;
			byte[] b = new byte[1024];
			while ((length = file.read(b)) > 0)
				bais.write(b, 0, length);
			if(file!=null) file.close();
			return bais.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			file=null;
			return null;
		}
	}
}
