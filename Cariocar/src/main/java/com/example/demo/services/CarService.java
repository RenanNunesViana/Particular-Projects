package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Car;

public interface CarService {
	
	public Optional<Car> saveCar(Car car, String cpf);
	public Car editCar(Car car, String plate);
	public List<Car> listCar();
	public Car getCar(String plate);
	public Optional<Car> deleteCar(String plate);
}
