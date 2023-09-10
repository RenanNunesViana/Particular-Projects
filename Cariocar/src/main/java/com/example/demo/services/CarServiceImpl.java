package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CarAlreadyExistException;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.exception.UserNotFoundException;
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
		//checking if user exist and if the car there isn't
		Optional<User> user = userRepository.findByCpf(cpf);
		Optional<Car> carTmp = carRepository.findById(car.getPlate());
		if(user.isEmpty())
			throw new UserNotFoundException(cpf);
		if(carTmp.isPresent())
			throw new CarAlreadyExistException(car.getPlate());
		
		// adding a the new car plate to the customer
		List<String> carPlateList = user.get().getCarsPlate();
		carPlateList.add(car.getPlate());
		user.get().setCarsPlate(carPlateList);
		userRepository.save(user.get());
		
		// setting the owner customer to the car
		car.setOwner(user.get());
		carRepository.save(car);

		return carRepository.findById(car.getPlate());
		
	}

	@Override
	public Car editCar(Car car, String plate) {
		Car carTmp = carRepository.findById(plate)
					.orElseThrow(()-> new CarNotFoundException(plate));
		carTmp.setAge(car.getAge());
		carTmp.setModel(car.getModel());
		carTmp.setOwner(car.getOwner());
		//if plates does not need be settled it just gone jump to save method
		//
		if(!car.getPlate().equals(plate)) {
			carTmp.setPlate(plate);
			carRepository.deleteById(plate);
			User user = carTmp.getOwner();
			List<String> plates = user.getCarsPlate();
			plates.remove(plate);
			user.setCarsPlate(plates);
			userRepository.save(user);
			carTmp.setOwner(user);
		}
		return carRepository.save(carTmp);
	}

	@Override
	public List<Car> listCar() {
		List<Car> cars = carRepository.findAll();
		return cars;
	}

	@Override
	public Car getCar(String plate) {
		return carRepository.findById(plate).
				orElseThrow(()-> new CarNotFoundException(plate));
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
