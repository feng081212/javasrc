package com.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipIOStream {

	public ZipOutputStream getZipOutputStream(OutputStream outputStream){
		return new ZipOutputStream(outputStream) ;
	}
	
	public void addEntry(ZipOutputStream zos,String entryName,InputStreamResource isAdapter){
		InputStream is = null ;
		try {
			if(zos == null || isAdapter == null){
				return ;
			}
			is = isAdapter.getInputStream() ;
			if(is == null){
				return ;
			}
			zos.putNextEntry(new ZipEntry(entryName));
			IOStream.getInstance().writeOutputStream(zos,is,false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				AutoCloseableFactory.close(is);
			}
		}
	}
	
	
}
