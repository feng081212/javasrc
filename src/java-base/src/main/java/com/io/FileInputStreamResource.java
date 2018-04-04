package com.io;

import com.file.ClassPathFileResource;

public class FileInputStreamResource extends ClassPathFileResource implements InputStreamResource {

	public FileInputStreamResource(String filename) {
		super(filename);
	}

	@Override
	public String getFilename() {
		String filename = super.getFilename() ;
		if(filename == null || filename.trim().length() == 0){
			return this.filename ;
		} else {
			return filename ;
		}
	}
}
