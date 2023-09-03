package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleValidationException {
	
	private static final String errorPage = "error";
	
	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundByCpfException(UserNotFoundException e) {
		return errorPage;
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public String userNotFoundExceptionById(UserNotFoundException e) {
		return errorPage;
	}
	
	@ExceptionHandler(CarNotFoundException.class)
	public String carNotFoundException(UserNotFoundException e) {
		return errorPage;
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public String orderNotFoundException(UserNotFoundException e) {
		return errorPage;
	}
	
	/*
	 * @ExceptionHandler(UserNotFoundException.class) public String
	 * notFoundException(UserNotFoundException e) { return errorPage; }
	 */
	
	
}
