package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.Order;
import com.cariocar.angular.cariocarangular.models.PaymentStats;
import com.cariocar.angular.cariocarangular.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        boolean exist = orderRepository.existsById(order.getId());
        if(!exist)
            return orderRepository.save(order);
        throw new IllegalArgumentException("order already exists");
    }

    @Override
    public Order editOrder(Order order) {
        boolean exist = orderRepository.existsById(order.getId());
        if(exist)
            orderRepository.save(order);
        throw new IllegalArgumentException("this order does not exist");
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Order> getOrderByDate(Date beg, Date end) {
        return null;
    }

    @Override
    public List<Order> getOrderByCustCpf(String cpf) {
        return null;
    }

    @Override
    public List<Order> getOrderByCar(String plate) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        orderRepository.delete(order);
    }

    @Override
    public void orderProgress(Long id, PaymentStats status, float payment) {
        Order order = orderRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        order.setCheckout(LocalDateTime.now());
        order.setPaymentStats(status);
        if(status.equals(PaymentStats.PARTIAL)){
            order.setPaymentMade(payment);
            order.setOutstanding(order.getQuote() - order.getPaymentMade());
            if(order.getPaymentMade() == order.getQuote())
                order.setPaymentStats(PaymentStats.CLOSED);
        } else if (status.equals(PaymentStats.CLOSED)) {
            order.setPaymentMade(order.getQuote());
            order.setOutstanding(0);
        }
        orderRepository.save(order);
    }
}