package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Customer;

public interface CustomerService {

	public Optional<Customer> saveCustomer(Customer customer);
	public List<Customer> listCustomer();
	public Optional<Customer> getCustomer(String cpf);
	public Optional<Customer> setCustomer(Customer customer);
	public Optional<Customer> deleteCustomer(String cpf);
	public Optional<Customer> addCar(String cpf, String plate);
	public Optional<Customer> deleteCar(String cpf, String plate);
}
