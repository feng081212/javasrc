package com.execption;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomException() {
        this(100002);
    }

	public CustomException(int code) {
		super(String.valueOf(code));
    }
	
	public CustomException(String message) {
		super(message);
    }
}
