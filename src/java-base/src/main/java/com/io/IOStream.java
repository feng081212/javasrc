package com.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class IOStream {

	public static final IOStream getInstance(){
		return new IOStream();
	}
	
	/**
	 * 读取输入流InputStream的内容
	 * @param InputStream 输入流
	 * @param closeIO 是否关闭IO流
	 * @return
	 */
	public byte[] readInputStream(InputStream inputStream,boolean closeIO) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
		try{
			writeOutputStream(bos, inputStream, false);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(closeIO){
				AutoCloseableFactory.close(inputStream);
			}
		}
		return bos.toByteArray() ;
	}
	
	/**
	 * 将byte数组的内容写入到输出流OutputStream中
	 * @param outputStream 输出流
	 * @param bytes 要输出到OutputStream中的byte数组
	 * @param closeIO 是否关闭IO流
	 * @return
	 * @throws Exception 
	 */
	public void writeOutputStream(OutputStream outputStream,byte[] bytes,boolean closeIO) {
		ByteArrayInputStream bais = null ;
		try{
			if(bytes == null || bytes.length == 0){
				return ;
			}
			bais = new ByteArrayInputStream(bytes) ;
			writeOutputStream(outputStream, bais, false);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			AutoCloseableFactory.close(bais);
			if(closeIO){
				AutoCloseableFactory.close(outputStream);
			}
		}
	}
	
	/**
	 * 将inputStream输入流中的内容读出，写入输出流OutputStream中
	 * @param outputStream  输出流
	 * @param inputStream   输入流
	 * @param closeIO 		是否关闭IO流
	 * @throws Exception
	 */
	public void writeOutputStream(OutputStream outputStream,InputStream inputStream,boolean closeIO){
		try{
			if(outputStream == null || inputStream == null){
				return ;
			}
			byte[] bytes = new byte[1024] ;
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream) ;
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
			int length = 0 ;
			while((length = bufferedInputStream.read(bytes)) > 0){
				bufferedOutputStream.write(bytes,0,length);
			}
			bufferedOutputStream.flush();
		} catch(Exception e) {
			e.printStackTrace() ;
		} finally {
			if(closeIO){
				AutoCloseableFactory.close(inputStream);
				AutoCloseableFactory.close(outputStream);
			}
		}
	}
	
	/**
	 * 将序列号对象转换为二进制数组
	 * @param serializable 序列号的对象
	 * @return
	 */
	public byte[] readSerializableObject(Serializable serializable){
		ByteArrayOutputStream baos = null ;
		ObjectOutputStream oos = null ;
		try{
			baos = new ByteArrayOutputStream() ;
			oos = new ObjectOutputStream(baos) ;
			oos.writeObject(serializable);
			return baos.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
			return new byte[0];
		}finally{
			AutoCloseableFactory.close(baos);
			AutoCloseableFactory.close(oos);
		}
	}
	
	/**
	 * 将二进制数组还原为序列化对象
	 * @param bytes
	 * @return
	 */
	public Serializable readSerializableObject(byte[] bytes){
		ByteArrayInputStream bais = null ;
		ObjectInputStream ois = null ;
		try{
			bais = new ByteArrayInputStream(bytes) ;
			ois = new ObjectInputStream(bais) ;
			return (Serializable) ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
			return null ;
		}finally{
			AutoCloseableFactory.close(bais);
			AutoCloseableFactory.close(ois);
		}
	}
	
}
