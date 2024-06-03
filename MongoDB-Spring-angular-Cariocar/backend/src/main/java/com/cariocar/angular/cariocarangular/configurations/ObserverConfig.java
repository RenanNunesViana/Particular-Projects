package com.cariocar.angular.cariocarangular.configurations;

import com.cariocar.angular.cariocarangular.observer.OrderNotifier;
import com.cariocar.angular.cariocarangular.observer.observers.OrderLogger;
import com.cariocar.angular.cariocarangular.observer.observers.OrderObserver;
import com.cariocar.angular.cariocarangular.services.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObserverConfig {
    @Bean
    public OrderNotifier orderNotifier() {
        return new OrderNotifier();
    }

    @Bean
    public CommandLineRunner init(OrderService orderService, OrderLogger orderLogger) {
        return args -> {
            orderService.addObserver(orderLogger);
        };
    }
}
