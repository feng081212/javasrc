package test;

import java.net.Socket;

public class SocketClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.2.122", 3307) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
