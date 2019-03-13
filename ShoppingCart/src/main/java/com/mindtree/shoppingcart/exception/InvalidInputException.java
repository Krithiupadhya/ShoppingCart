package com.mindtree.shoppingcart.exception;

public class InvalidInputException extends ApplicationException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8898361340284517063L;

	public InvalidInputException(String message) {
		super(message);
	}
	
	public InvalidInputException(String message, String uri, Exception cause) {
		super(message, uri, cause);
	}

}
