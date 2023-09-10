package com.example.demo.exception;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String cpf){
		super("fadsfasd");
	}
	
	public UserNotFoundException(Long id) {
		super("fdsfasdgf");
	}
}
