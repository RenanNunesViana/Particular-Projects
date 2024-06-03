package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Car;
import com.example.demo.repositories.CarRepository;

@SpringBootTest
public class CarServiceImplTest {
    
    @InjectMocks
    CarServiceImpl carService;
    
    @Mock
    CarRepository carRepository;

    @Test
    void testSaveCar() {
        Car newCar = new Car();
        //Optional<Car> optCar = Optional.of(newCar);
        when(carRepository.save(newCar)).thenReturn(newCar);
        Car savedCar = carService.saveCar(newCar, Mockito.anyString()).get();

        assertEquals(newCar, savedCar);
    }

    @Test
    void testDeleteCar() {
        
    }

    @Test
    void testEditCar() {

    }

    @Test
    void testGetCar() {

    }

    @Test
    void testListCar() {

    }
}
