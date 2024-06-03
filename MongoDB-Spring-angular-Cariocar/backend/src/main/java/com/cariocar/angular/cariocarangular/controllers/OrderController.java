package com.cariocar.angular.cariocarangular.controllers;
import com.cariocar.angular.cariocarangular.dtos.OrderDTO;
import com.cariocar.angular.cariocarangular.factories.ConcreteFactory;
import com.cariocar.angular.cariocarangular.models.Order;
import com.cariocar.angular.cariocarangular.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cariocar/order")
@CrossOrigin("http://localhost:4200/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody OrderDTO orderDTO){
        Order order = ConcreteFactory.createOrder(orderDTO);
        return orderService.createOrder(order);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> listOrders(){
        return orderService.listOrders();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Order getOrder(@PathVariable ObjectId id){
        return orderService.getOrder(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order editOrder(@PathVariable Long id, @RequestBody Order order){
        return orderService.editOrder(order);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable ObjectId id){
        orderService.deleteOrder(id);
    }
}


