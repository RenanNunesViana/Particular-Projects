package com.example.demo.exception;

public class CarAlreadyExistException extends IllegalArgumentException{
	
	private static final long serialVersionUID = 1L;
	
	public CarAlreadyExistException(String plate){
		super(String.format("carro com placa %s já registrado no sistema", plate));
	}
}
