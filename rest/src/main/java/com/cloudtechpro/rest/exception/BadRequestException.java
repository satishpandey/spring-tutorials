package com.cloudtechpro.rest.exception;

/**
 * Exception handler for handling client side Bad requests.
 * 
 * @author satish
 *
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1791564636123821405L;

	public BadRequestException(String message) {
		super(message);
	}

}
