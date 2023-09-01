package com.example.demo.model;

public class CarRegister {

	Car car;
	String ownerCpf;
	public CarRegister() {
		this.car = new Car();
		this.ownerCpf = "";
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getOwnerCpf() {
		return ownerCpf;
	}
	public void setOwnerCpf(String ownerCpf) {
		this.ownerCpf = ownerCpf;
	}
	
	
}

