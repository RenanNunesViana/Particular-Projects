package com.example.demo.exception;

public class OrderNotFoundException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(Long id){
		super(String.format("ordem %d não encontrada", id));
	}
}
