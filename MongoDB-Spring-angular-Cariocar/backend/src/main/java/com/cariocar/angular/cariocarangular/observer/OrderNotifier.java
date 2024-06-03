package com.cariocar.angular.cariocarangular.observer;

import com.cariocar.angular.cariocarangular.models.Order;
import com.cariocar.angular.cariocarangular.observer.observers.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderNotifier {
    private final List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.notify(order);
        }
    }
}
