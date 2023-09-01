package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Optional<Customer> saveCustomer(Customer customer) {
		Optional<Customer> tempCustomer = customerRepository.findById(customer.getCpf());
		if (tempCustomer.isEmpty()) {
			customer.setCarsPlate(new ArrayList<>());
			String cpf = customerRepository.save(customer).getCpf();
			return getCustomer(cpf);
		}
		Customer customerObj = tempCustomer.get();
		customerObj.setName(customer.getName());
		customerObj.setCarsPlate(customer.getCarsPlate());
		customerObj.setCel(customer.getCel());
		customerObj.setEmail(customer.getEmail());
		customerObj.setPoints(customer.getPoints());
		customerObj.setCpf(customer.getCpf());
		
		return customerRepository.findById(customerRepository.save(customerObj).getCpf());
	}

	@Override
	public List<Customer> listCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomer(String cpf) {
		return customerRepository.findById(cpf);
	}

	@Override
	public Optional<Customer> setCustomer(Customer customer) {
		return saveCustomer(customer);
	}

	@Override
	public Optional<Customer> deleteCustomer(String cpf) {
		Optional<Customer> customer = customerRepository.findById(cpf);
		customerRepository.delete(customer.get());
		
		return customer;
	}

	@Override
	public Optional<Customer> addCar(String cpf, String plate) {
		// saving a new car and defining its owner
		Customer customer = getCustomer(cpf).get();
		List<String> customerCarsList = customer.getCarsPlate();
		customerCarsList.add(plate);
		customer.setCarsPlate(customerCarsList);
		customerRepository.save(customer);

		return getCustomer(cpf);

	}

	@Override
	public Optional<Customer> deleteCar(String cpf, String plate) {
		Customer customer = getCustomer(cpf).get();
		List<String> carList = customer.getCarsPlate();
		carList.remove(plate);
		customer.setCarsPlate(carList);
		customerRepository.save(customer);

		return getCustomer(cpf);
	}

}
