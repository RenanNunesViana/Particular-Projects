package com.cariocar.angular.cariocarangular.factories;

import com.cariocar.angular.cariocarangular.dtos.CarDTO;
import com.cariocar.angular.cariocarangular.dtos.OrderDTO;
import com.cariocar.angular.cariocarangular.dtos.UserDTO;
import com.cariocar.angular.cariocarangular.models.Car;
import com.cariocar.angular.cariocarangular.models.Order;
import com.cariocar.angular.cariocarangular.models.User;

public class ConcreteFactory {
    public static User createCliente(UserDTO dto) {
        User client = new User(
                dto.cpf(),
                dto.firstName(),
                dto.lastName(),
                dto.cel(),
                dto.email(),
                dto.role(),
                dto.password()
        );

        return client;
    }

    public static Car createCar(CarDTO dto) {
        Car car = new Car(
                dto.plate(),
                dto.model(),
                dto.age(),
                dto.ownerCpf()
        );

        return car;
    }

    public static Order createOrder(OrderDTO dto) {
        Order order = new Order(
                dto.description(),
                dto.note(),
                dto.checkin(),
                dto.checkout(),
                dto.customerCpf(),
                dto.carPlate(),
                dto.paymentStats(),
                dto.quote(),
                dto.paymentMade(),
                dto.outstanding()
        );
        return order;
    }
}
