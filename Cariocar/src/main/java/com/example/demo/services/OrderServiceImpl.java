package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Optional<Order> createOrder(Order order) {
		orderRepository.save(order);
		return orderRepository.findById(order.getId());
	}

	@Override
	public Optional<Order> editOrder(Long id, Order order) {
		Order orderTmp = orderRepository.findById(id).get();
		orderTmp.setCheckin(order.getCheckin());
		orderTmp.setCheckout(order.getCheckout());
		orderTmp.setCarPlate(order.getCarPlate());
		orderTmp.setCustomerCpf(order.getCustomerCpf());
		orderTmp.setDescription(order.getDescription());
		orderTmp.setNote(order.getNote());
		orderTmp.setPaymentStats(order.getPaymentStats());
		orderTmp.setQuote(order.getQuote());
		
		orderRepository.save(orderTmp);
		
		return orderRepository.findById(orderTmp.getId());
	}

	@Override
	public List<Order> listOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> getOrder(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public List<Order> getOrderByDate(Date beg, Date end) {
		return orderRepository.findByCheckinBetween(beg, end);
	}

	@Override
	public List<Order> getOrderByCustCpf(String cpf) {
		return orderRepository.findBycustomerCpf(cpf);
	}

	@Override
	public List<Order> getOrderByCar(String plate) {
		return orderRepository.findBycarPlate(plate);
	}

	@Override
	public Optional<Order> deleteOrder(Long id) {
		Optional<Order> orderTmp = orderRepository.findById(id);
		orderRepository.delete(orderTmp.get());
		return orderTmp;
	}
	
	@Override
	public void finishOrder(Long id) {
		Order order = orderRepository.findById(id).get();
		order.setCheckout(new Date());
		orderRepository.save(order);
	}

}
