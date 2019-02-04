package com.cloudtechpro.rest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handling controller class.
 * 
 * @author satish
 *
 */
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ExceptionResponse> numberFormat(NumberFormatException ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("BadRequest");
		response.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This handler uses spring in-build objects for preparing the exception message
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> resourceConflict(BadRequestException ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("BadRequest");
		response.setErrorMessage(ex.getMessage());
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * This handler returns a custom exception message with custom fields
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("Item not found");
		response.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.EXPECTATION_FAILED);
	}

}
