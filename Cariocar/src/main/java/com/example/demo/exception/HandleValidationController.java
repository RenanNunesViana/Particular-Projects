package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleValidationController {
	
	private static final String ERRORPAGE = "error";
	
	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundException(UserNotFoundException e) {
		return ERRORPAGE;
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public String userAlreadyExistException(UserAlreadyExistException e) {
		return ERRORPAGE;
	}
	
	@ExceptionHandler(CarNotFoundException.class)
	public String carNotFoundException(CarNotFoundException e) {
		return ERRORPAGE;
	}
	
	@ExceptionHandler(CarAlreadyExistException.class)
	public String carAlreadyExistException(CarAlreadyExistException e) {
		return ERRORPAGE;
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public String orderNotFoundException(OrderNotFoundException e) {
		return ERRORPAGE;
	}
	
	@ExceptionHandler(OrderAlreadyExistException.class)
	public String orderAlreadyExistException(OrderAlreadyExistException e) {
		return ERRORPAGE;
	}
	
	
}
