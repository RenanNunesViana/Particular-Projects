package com.example.demo.exception;

public class OrderAlreadyExistException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	
	public OrderAlreadyExistException(Long id) {
		super(String.format("Ordem com id %d já registrada no sistema", id));
	}
}
