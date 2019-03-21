package com.mindtree.shoppingcart.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.shoppingcart.dto.ErrorResponse;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	private static Logger logger=LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException e , HttpServletRequest request ){
		ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), e.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException e , HttpServletRequest request ){
		ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), e.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException e , HttpServletRequest request ){
		ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), e.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception e , HttpServletRequest request ){
		logger.error(e.getMessage(), e);
		ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), e.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
