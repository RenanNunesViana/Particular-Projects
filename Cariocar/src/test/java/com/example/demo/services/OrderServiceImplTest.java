package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Car;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

@SpringBootTest
public class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;
    
    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    CarRepository carRepository;
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    void testCreateOrder() {
        Order newOrder = new Order();
        when(orderRepository.save(newOrder)).thenReturn(newOrder);
        
        /* User user = new User();
        Optional<User> optUser = Optional.of(user);
        when(userRepository.findByCpf(newOrder.getCustomerCpf())).thenReturn(optUser);

        Car car = new Car();
        Optional<Car> optCar = Optional.of(car);
        when(carRepository.findById(newOrder.getCarPlate())).thenReturn(optCar); */

        Order createdOrder = orderService.createOrder(newOrder);
        assertEquals(newOrder, createdOrder);
        assertNotNull(createdOrder);
        /*verify(userRepository, times(1)).findByCpf(user.getCpf());
        verify(carRepository, times(1)).findById(car.getPlate()); */
    }

    @Test
    void testDeleteOrder() {

    }

    @Test
    void testEditOrder() {

    }

    @Test
    void testFinishOrder() {

    }

    @Test
    void testGetOrder() {

    }

    @Test
    void testGetOrderByCar() {

    }

    @Test
    void testGetOrderByCustCpf() {

    }

    @Test
    void testGetOrderByDate() {

    }

    @Test
    void testListOrders() {

    }
}
