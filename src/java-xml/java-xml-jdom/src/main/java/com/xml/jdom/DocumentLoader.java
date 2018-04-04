package com.xml.jdom;

import java.io.InputStream;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import com.io.FileInputStreamResource;
import com.log.LogFactory;


public class DocumentLoader {

	public static final DocumentLoader getInstance(){
		return new DocumentLoader() ;
	}
	
	/**
	 * 读取XML文件，返回Document对象
	 * @param fileName 绝对路径
	 * @return
	 */
	public Document getDocument(String fileName){
		try(InputStream inputStream = new FileInputStreamResource(fileName).getInputStream())  {
			return getDocument(inputStream) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 读取XML输入流，返回Document对象
	 * @param inputStream XML输入流
	 * @return
	 */
	public Document getDocument(InputStream inputStream){
		try{
			if(inputStream != null){
				return new SAXBuilder().build(inputStream) ;
			}
		}catch(Exception e){
			LogFactory.error(e);
		}
		return null ;
	}
}
