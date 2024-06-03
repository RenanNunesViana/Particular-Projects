package com.cariocar.angular.cariocarangular.controllers;

import com.cariocar.angular.cariocarangular.dtos.CarDTO;
import com.cariocar.angular.cariocarangular.factories.ConcreteFactory;
import com.cariocar.angular.cariocarangular.models.Car;
import com.cariocar.angular.cariocarangular.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cariocar/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping(value = "/addcar")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCar(@RequestBody CarDTO carDTO){
        Car car = ConcreteFactory.createCar(carDTO);
        carService.saveCar(car);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Car> listCars(){
        return carService.listCar();
    }

    @GetMapping(value = "/{plate}")
    @ResponseStatus(HttpStatus.FOUND)
    public Car getCar(@PathVariable Long plate){
        return carService.getCar(plate);
    }

    @PutMapping(value = "/{plate}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Car editCar(@PathVariable Long plate, @RequestBody Car car){
        return carService.editCar(car, plate);
    }

    @DeleteMapping(value = "delete/{plate}")
    @ResponseStatus(HttpStatus.OK)
    public Car deleteCar(@PathVariable Long plate){
        return carService.deleteCar(plate);
    }
}
