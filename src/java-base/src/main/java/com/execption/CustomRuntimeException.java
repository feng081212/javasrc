package com.execption;

public class CustomRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomRuntimeException() {
        this(100002);
    }

	public CustomRuntimeException(int code) {
		super(String.valueOf(code));
    }
	
	public CustomRuntimeException(String message) {
		super(message);
    }
}
