package test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class IOStreamUtil {
	
	public static IOStreamUtil getInstance(){
		return new IOStreamUtil();
	}
	
	public byte[] readInputStream(InputStream inputStream,boolean close){
		ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
		BufferedInputStream buffer = null ;
		try{
			if(inputStream == null)
				return bos.toByteArray();
			byte[] b = new byte[1024];
			int length = 0 ;
			buffer = new BufferedInputStream(inputStream);
			while((length = buffer.read(b)) > 0){
				bos.write(b,0,length);
			}
		}catch(Exception e){
			
		}finally{
			if(close) closeIO(inputStream);
			closeIO(buffer);
		}
		return bos.toByteArray() ;
	}
	
	public byte[] readInputStream(InputStream inputStream){
		return readInputStream(inputStream,false) ;
	}
	
	public String readInputStream(InputStream inputStream,String charset){
		return readInputStream(inputStream,false,charset);
	}
	
	public String readInputStream(InputStream inputStream,boolean close,String charset){
		try{
			return new String(readInputStream(inputStream),charset);
		}catch(Exception e){
			return "" ;
		}
	}

	public byte[] readSerializableObject(Object obj){
		ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
		ObjectOutputStream oos = null ;
		try{
			oos = new ObjectOutputStream(baos) ;
			oos.writeObject(obj);
		}catch(Exception e){
			
		}finally{
			closeIO(oos);
		}
		return baos.toByteArray();
	}
	
	public Object readSerializableObject(byte[] obj){
		ByteArrayInputStream bais = null ;
		ObjectInputStream ois = null ;
		try{
			bais = new ByteArrayInputStream(obj) ;
			ois = new ObjectInputStream(bais) ;
			return ois.readObject();
		}catch(Exception e){
			return null ;
		}finally{
			closeIO(ois);
		}
	}
	
	public void closeIO(InputStream is){
		try{
			if(is == null) return ;
			is.close();
			is = null ;
		}catch(Exception e){
			is = null ;
		}
	}
	
	public void closeIO(OutputStream os){
		try{
			if(os == null) return ;
			os.flush();
			os.close();
			os = null ;
		}catch(Exception e){
			os = null ;
		}
	}
	
}
