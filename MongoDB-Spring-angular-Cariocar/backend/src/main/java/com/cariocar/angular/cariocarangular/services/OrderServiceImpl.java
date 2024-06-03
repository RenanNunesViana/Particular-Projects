package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.Order;
import com.cariocar.angular.cariocarangular.models.PaymentStats;
import com.cariocar.angular.cariocarangular.observer.OrderNotifier;
import com.cariocar.angular.cariocarangular.observer.observers.OrderObserver;
import com.cariocar.angular.cariocarangular.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final OrderNotifier notifier;

   /* @Autowired
    public void init(OrderLogger orderLogger) {
        notifier.addObserver(orderLogger);
    }*/

    @Override
    public Order createOrder(Order order) {
            Order savedOrder = orderRepository.save(order);
            notifier.notifyObservers(savedOrder);
            return savedOrder;
    }

    @Override
    public void addObserver(OrderObserver observer) {
        notifier.addObserver(observer);
    }

    @Override
    public void removeObserver(OrderObserver observer) {
        notifier.removeObserver(observer);
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
    public Order getOrder(ObjectId id) {
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
    public void deleteOrder(ObjectId id) {
        Order order = orderRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        orderRepository.delete(order);
    }

    @Override
    public void orderProgress(ObjectId id, PaymentStats status, float payment) {
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