package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

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

	//auxiliar method to remove or add cars from users. command must be rmv or add.
	private void alterCarToUser(Car car, User user, String command){
		List<Car> cars = user.getCars();
		
		if(command.equals("add")){
			cars.add(car);
			user.setCars(cars);
			car.setOwner(user);
			userRepository.save(user);
			carRepository.save(car);
		}else
		if(command.equals("rmv")){
			cars.remove(car);
			user.setCars(cars);
			userRepository.save(user);
			carRepository.delete(car);
		}else
			throw new RuntimeErrorException(new Error("use rmv to remove or add to save"));
	}

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
		List<Car> carsList = user.get().getCars();
		carsList.add(car);
		user.get().setCars(carsList);
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
		//if plates does not need be settled it just gone jump to save method
		if(!car.getPlate().equals(plate)) {
			alterCarToUser(carTmp, carTmp.getOwner(), "rmv");
			carTmp.setPlate(car.getPlate());
			alterCarToUser(carTmp, carTmp.getOwner(), "add");
			return carTmp;
		}else{
			return carRepository.save(carTmp);
		}
		
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
	public Car deleteCar(String plate) {
		Car car = carRepository.findById(plate)
					.orElseThrow(()-> new CarNotFoundException(plate));
		
		// deleting car plate from the owner list of cars plate and
		// removing it from DB
		alterCarToUser(car, car.getOwner(), "rmv");
		return car;
	}


}
