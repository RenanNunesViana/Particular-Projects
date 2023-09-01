package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

List<Order> findByCheckinBetween(Date date1, Date date2);
List<Order> findBycustomerCpf(String cpf);
List<Order> findBycarPlate(String plate);
}
