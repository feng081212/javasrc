package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class DefaultFileResource implements FileResource {

	protected String filename = "" ;
	
	public DefaultFileResource(String filename){
		this.filename = filename ;
	}
	
	@Override
	public String getFilename() {
		return this.filename ;
	}
	
	@Override
	public File getFile() {
		return new File(getFilename()) ;
	}

	@Override
	public InputStream getInputStream() {
		try {
			return new FileInputStream(getFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
}
