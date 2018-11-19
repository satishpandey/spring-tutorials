package com.cloudtechpro.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handling controller class.
 * 
 * @author satish
 *
 */
@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> resourceConflict(BadRequestException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("BadRequest");
		response.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("NoContent");
		response.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NO_CONTENT);
	}

}
