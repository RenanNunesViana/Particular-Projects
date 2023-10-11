package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.Order;
import com.cariocar.angular.cariocarangular.models.PaymentStats;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    public Order createOrder(Order order);
    public Order editOrder(Order order);
    public List<Order> listOrders();
    public Order getOrder(Long id);
    public List<Order> getOrderByDate(Date beg, Date end);
    public List<Order> getOrderByCustCpf(String cpf);
    public List<Order> getOrderByCar(String plate);
    public void deleteOrder(Long id);
    public void orderProgress(Long id, PaymentStats status, float payment);
}
