package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    public void saveCar(Car car);
    public Car editCar(Car car, Long plate);
    public List<Car> listCar();
    public Car getCar(Long plate);
    public Car deleteCar(Long plate);
}
