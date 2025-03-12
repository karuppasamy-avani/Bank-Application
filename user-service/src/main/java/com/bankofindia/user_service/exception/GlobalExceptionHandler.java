package com.bankofindia.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<String> handleResourceNotFound(ResourceNotFound ex)
	{
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ex.getMessage());
	}
	
//	@ExceptionHandler(ResourceConflict.class)
//	public ResponseEntity<String> handleRestControllerAdvice(ResourceConflict ex)
//	{
//		return ResponseEntity.status(HttpStatus.CONFLICT)
//				.body(ex.getMessage());
//	}
}
