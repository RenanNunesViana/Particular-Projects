package com.example.demo.exception;

public class UserNotFoundException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String cpf){
		super(String.format("usuario com cpf %s não encontrado", cpf));
	}
	
	public UserNotFoundException(Long id) {
		super(String.format("usuario %d não encontrado", id));
	}
}
