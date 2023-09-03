package com.example.demo.exception;

public class UserAlreadyExistException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistException(String cpf) {
		super(String.format("user with cpf %d already exist", cpf));
	}

}
