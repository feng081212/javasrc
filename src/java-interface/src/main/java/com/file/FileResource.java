package com.file;

import java.io.File;

import com.io.InputStreamResource;

public interface FileResource extends InputStreamResource {

	String getFilename() ;
	
	File getFile() ;
}
