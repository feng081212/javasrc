package com.file;

import java.net.URL;

import com.log.LogFactory;

public class ClassPathFileResource extends DefaultFileResource implements FileResource {

	public ClassPathFileResource(String filename) {
		super(filename);
	}

	@Override
	public String getFilename() {
		try{
			URL url = ClassLoader.getSystemResource(filename) ;
			if(url == null){
				LogFactory.error(filename + "文件不存在");
				return null ;
			}
			return url.getFile() ;
		}catch(Exception e){
			LogFactory.error(e);
		}
		return null ;
	}
}
