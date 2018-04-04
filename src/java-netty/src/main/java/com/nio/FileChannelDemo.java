package com.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

	public void read(String filepath) throws Exception{
		RandomAccessFile file = new RandomAccessFile(filepath, "rw") ;
		FileChannel inChannel = file.getChannel() ;
		//create buffer with capacity of 1024 bytes
		ByteBuffer buf = ByteBuffer.allocate(1024);
		//read into buffer
		int bytesRead = inChannel.read(buf) ;
		while(bytesRead > 0){
			//make buffer ready for read
			buf.flip();
	  		while(buf.hasRemaining()){
	  			 // read 1 byte at a time
	      		System.out.print((char) buf.get());
	  		}
	  		//make buffer ready for writing
	  		buf.clear();
	  		bytesRead = inChannel.read(buf);
	  		System.out.print("----" + bytesRead);
		}
		file.close();
	}
	
	public static void main(String[] args) throws Exception {
		new FileChannelDemo().read("D:\\lua脚本.txt");
	}
}
