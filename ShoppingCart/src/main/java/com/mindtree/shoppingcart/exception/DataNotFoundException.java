package com.mindtree.shoppingcart.exception;

public class DataNotFoundException extends ApplicationException {
	
	private static final long serialVersionUID = 3699795328163093644L;

	public DataNotFoundException(String message, String uri, Exception cause) {
		super(message, uri, cause);
	}
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
