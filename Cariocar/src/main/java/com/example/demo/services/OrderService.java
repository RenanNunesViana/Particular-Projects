package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Order;

public interface OrderService {
	public Order createOrder(Order order);
	public Order editOrder(Long id, Order order);
	public List<Order> listOrders();
	public Order getOrder(Long id);
	public List<Order> getOrderByDate(Date beg, Date end);
	public List<Order> getOrderByCustCpf(String cpf);
	public List<Order> getOrderByCar(String plate);
	public Optional<Order> deleteOrder(Long id);
	public void finishOrder(Long id);
}
