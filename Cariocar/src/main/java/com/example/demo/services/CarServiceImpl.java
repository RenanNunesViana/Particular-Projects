package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Car;
import com.example.demo.model.User;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.UserRepository;


@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<Car> saveCar(Car car, String cpf) {
		// adding a the new car plate to the customer
		User user = userRepository.findByCpf(cpf).get();
		List<String> carPlateList = user.getCarsPlate();
		carPlateList.add(car.getPlate());
		user.setCarsPlate(carPlateList);
		userRepository.save(user);
		
		// setting the owner customer to the car
		car.setOwner(user);
		carRepository.save(car);
		
		return carRepository.findById(car.getPlate());
		
	}

	@Override
	public Optional<Car> editCar(Car car) {
		/*Car carTmp = carRepository.findById(car.getPlate()).get();
		carTmp.setAge(car.getAge());
		carTmp.setModel(car.getModel());
		String plate = car.getPlate();
		carRepository.deleteById(plate);
		*/
		carRepository.save(car);
		return carRepository.findById(car.getPlate());
	}

	@Override
	public List<Car> listCar() {
		List<Car> cars = carRepository.findAll();
		return cars;
	}

	@Override
	public Optional<Car> getCar(String plate) {
		Optional<Car> car = carRepository.findById(plate);
		return car;
	}

	@Override
	public Optional<Car> deleteCar(String plate) {
		Optional<Car> car = carRepository.findById(plate);
		
		// deleting car plate from the owner list of cars plate 
		User user = car.get().getOwner();
		List<String> plateListCustomer = user.getCarsPlate();
		plateListCustomer.remove(plate);
		user.setCarsPlate(plateListCustomer);
		userRepository.save(user);
		
	
		carRepository.deleteById(plate);
		return car;
	}


}
