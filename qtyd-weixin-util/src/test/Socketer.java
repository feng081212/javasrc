package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Socketer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null ;
		Socket socket = null ;
		try {
			serverSocket = new ServerSocket(3307) ;
			socket = serverSocket.accept() ;
			IOStreamUtil ioStreamUtil = IOStreamUtil.getInstance() ;
			socket.getOutputStream().write(("socket success").getBytes("UTF-8"));
			while(true){
				String in = ioStreamUtil.readInputStream(socket.getInputStream(),"UTF-8") ;
				System.out.println(in);
				socket.getOutputStream().write(("get:" + in).getBytes("UTF-8"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				socket.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
