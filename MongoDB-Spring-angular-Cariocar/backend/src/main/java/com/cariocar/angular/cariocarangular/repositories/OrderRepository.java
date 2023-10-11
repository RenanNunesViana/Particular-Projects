package com.cariocar.angular.cariocarangular.repositories;

import com.cariocar.angular.cariocarangular.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
