package com.common.util;

public class Test extends Thread {
	public void run() {
		printMsg();
		interrupt();
	}

	public void printMsg() {
		Thread t = Thread.currentThread();
		String name = t.getName();
		System.out.println("name = " + name);
	}

	public static void main(String[] args) {
	}
}
