package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.OrderAlreadyExistException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {
		Optional<Order> orderTmp = orderRepository.findById(order.getId());
		if(orderTmp.isEmpty())
			return orderRepository.save(order);
		throw new OrderAlreadyExistException(order.getId());
		
				
	}

	@Override
	public Order editOrder(Long id, Order order) {
		Optional<Order> orderOpt = orderRepository.findById(id);
		if (orderOpt.isPresent()) {
			Order orderTmp = orderOpt.get();
			orderTmp.setCheckin(order.getCheckin());
			orderTmp.setCheckout(order.getCheckout());
			orderTmp.setCarPlate(order.getCarPlate());
			orderTmp.setCustomerCpf(order.getCustomerCpf());
			orderTmp.setDescription(order.getDescription());
			orderTmp.setNote(order.getNote());
			orderTmp.setPaymentStats(order.getPaymentStats());
			orderTmp.setQuote(order.getQuote());

			orderRepository.save(orderTmp);
			return orderTmp;
		}else
			throw new OrderNotFoundException(id);
		
	}

	@Override
	public List<Order> listOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrder(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(()-> new OrderNotFoundException(id));
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
		if(orderTmp.isPresent()) {
			orderRepository.delete(orderTmp.get());
			return orderTmp;
		}else
			throw new OrderNotFoundException(id);
		
	}

	@Override
	public void finishOrder(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if(order.isPresent()) {		
			order.get().setCheckout(new Date());
			orderRepository.save(order.get());
		}else
			throw new OrderNotFoundException(id);
	}

}
