package com.example.demo.exception;

public class CarNotFoundException extends IllegalArgumentException{
	
	private static final long serialVersionUID = 1L;
	
	public CarNotFoundException(String plate){
		super(String.format("carro com placa %s não encontrado", plate));
	}
}
