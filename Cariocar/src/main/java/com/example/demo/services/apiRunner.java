package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Car;
import com.example.demo.model.Order;
import com.example.demo.model.User;

@Component
public class apiRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.createUser(new User("123456789", "123456", "Renan", "Nunes Viana"));
        carService.saveCar(new Car("123456","gol",2021), "123456789");
        orderService.createOrder(new Order());
    }
    
}
