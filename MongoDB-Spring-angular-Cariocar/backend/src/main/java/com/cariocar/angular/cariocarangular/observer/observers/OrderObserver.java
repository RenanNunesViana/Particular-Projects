package com.cariocar.angular.cariocarangular.observer.observers;

import com.cariocar.angular.cariocarangular.models.Order;

public interface OrderObserver {
    void notify(Order order);
}
