package com.cariocar.angular.cariocarangular.repositories;

import com.cariocar.angular.cariocarangular.models.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, Long> {
}
