package com.mindtree.shoppingcart.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 4245556325441178463L;
	private String requestUri;
	
	public ApplicationException(String message, String uri, Exception cause) {
		super(message, cause);
		requestUri=uri;
	}
	
	public ApplicationException(String message) {
		super(message);
	}
 }
