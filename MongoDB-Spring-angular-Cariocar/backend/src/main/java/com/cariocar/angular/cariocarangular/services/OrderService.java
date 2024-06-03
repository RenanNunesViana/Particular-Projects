package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.Order;
import com.cariocar.angular.cariocarangular.models.PaymentStats;
import com.cariocar.angular.cariocarangular.observer.observers.OrderObserver;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public interface OrderService {
    public Order createOrder(Order order);
    public void addObserver(OrderObserver observer);
    public void removeObserver(OrderObserver observer);
    public Order editOrder(Order order);
    public List<Order> listOrders();
    public Order getOrder(ObjectId id);
    public List<Order> getOrderByDate(Date beg, Date end);
    public List<Order> getOrderByCustCpf(String cpf);
    public List<Order> getOrderByCar(String plate);
    public void deleteOrder(ObjectId id);
    public void orderProgress(ObjectId id, PaymentStats status, float payment);
}
