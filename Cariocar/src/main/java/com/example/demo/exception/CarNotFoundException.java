package com.example.demo.exception;

import jakarta.persistence.EntityNotFoundException;

public class CarNotFoundException extends EntityNotFoundException{
	
	private static final long serialVersionUID = 1L;
	
	public CarNotFoundException(String plate){
		super(String.format("carro com placa %s não encontrado", plate));
	}
}
