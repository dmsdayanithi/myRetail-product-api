package com.myretail.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * This class will handle exceptions thrown in various part of the application.
 *
 * @author Dayanithi Devarajan
 */
@ControllerAdvice
public class MyRetailExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyRetailExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<String> handleValidationException(final MethodArgumentNotValidException exception) {
		LOGGER.error("Invalid request parameters", exception);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product detail");
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	ResponseEntity<String> handleArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception) {
		LOGGER.error("Invalid request parameter type", exception);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	ResponseEntity<String> handleMethodNotSupportedException(final HttpRequestMethodNotSupportedException exception) {
		LOGGER.error("Method not supported exception", exception);
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exception.getMethod());
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<String> handleException(final Exception exception) {
		LOGGER.error("Unhandled exception occurred", exception);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred");
	}
}
