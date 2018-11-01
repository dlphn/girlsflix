package com.gfx.helper;

public class LoginExistsException extends Exception {
	
	/**
	 * Exception thrown when Login already taken
	 */
	private static final long serialVersionUID = 1L;

	public LoginExistsException() {
		super();
	}

	public LoginExistsException(String msg) {
		super(msg);
		
	}
}
