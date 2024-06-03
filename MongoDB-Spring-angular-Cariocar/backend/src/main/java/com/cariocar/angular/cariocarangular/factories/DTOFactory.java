package com.cariocar.angular.cariocarangular.factories;

import com.cariocar.angular.cariocarangular.dtos.CarDTO;
import com.cariocar.angular.cariocarangular.dtos.OrderDTO;
import com.cariocar.angular.cariocarangular.dtos.UserDTO;
import com.cariocar.angular.cariocarangular.models.Car;
import com.cariocar.angular.cariocarangular.models.Order;
import com.cariocar.angular.cariocarangular.models.User;

public class DTOFactory {
    public static UserDTO createClienteDTO(User user) {
        UserDTO dto = new UserDTO(
                user.getCpf(),
                user.getFirstName(),
                user.getLastName(),
                user.getCel(),
                user.getEmail(),
                user.getRole(),
                user.getPassword()
        );

        return dto;
    }

    public static CarDTO createCarroDTO(Car car) {
        CarDTO dto = new CarDTO(
                car.getPlate(),
                car.getModel(),
                car.getAge(),
                car.getOwnerCpf()
        );

        return dto;
    }

    public static OrderDTO createUserDTO(Order order) {
        OrderDTO dto = new OrderDTO(
                order.getDescription(),
                order.getNote(),
                order.getCheckin(),
                order.getCheckout(),
                order.getCustomerCpf(),
                order.getCarPlate(),
                order.getPaymentStats(),
                order.getQuote(),
                order.getPaymentMade(),
                order.getOutstanding()
        );
        return dto;
    }
}
