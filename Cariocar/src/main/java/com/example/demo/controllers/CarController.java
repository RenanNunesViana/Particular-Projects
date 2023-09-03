package com.example.demo.controllers;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Car;
import com.example.demo.model.CarRegister;
import com.example.demo.services.CarService;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/car")
@CrossOrigin
public class CarController {

	@Autowired
	CarService carService;

	@Autowired
	UserService userService;

	@GetMapping(value = "/register")
	public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
		CarRegister car = new CarRegister();
		model.addAttribute("car", car);
		return "carsRegister";
	}

	@PostMapping(value = "/register")
	public String createNewCar(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("carRegister") CarRegister carRegister) {

		try {
			Car car = carRegister.getCar();
			String cpf = carRegister.getOwnerCpf();
			Optional<Car> newCar = carService.getCar(car.getPlate());

			if (newCar.isPresent())
				return "redirect:/car/errors/idError";
			carService.saveCar(car, cpf);

			return "redirect:/car/list";

		} catch (Exception e) {
			return "redirect:/car/errors/idError";
		}

	}

	@GetMapping(value = "/list")
	public String listCars(Model model) {
		List<Car> carList = carService.listCar();
		model.addAttribute("cars", carList);
		return "carsRegistered";
	}

	@GetMapping(value = "/{plate}")
	public String getCar(@PathVariable String plate, Model model) {
		Optional<Car> carTmp = carService.getCar(plate);
		if (carTmp.isEmpty())
			return "redirect:/car/errors/plateError";
		try {
			model.addAttribute("car", carTmp.get());
			return "redirect:/car/singleCarRegistered";
		} catch (Exception e) {
			return "redirect:/car/errors/plateError";
		}
	}

	@PutMapping
	public String editCar(@RequestBody Car car) {
		Optional<Car> carTmp = carService.getCar(car.getPlate());
		if (carTmp.isEmpty())
			return "redirect:/car/errors/plateError";
		try {
			carService.editCar(car);
			return "redirect:/car/carsRegistered";
		} catch (Exception e) {
			return "redirect:/car/errors/plateError";
		}

	}

	@DeleteMapping(value = "/{plate}")
	public String deleteCar(@PathVariable String plate) {
		Optional<Car> carTmp = carService.getCar(plate);
		if (carTmp.isEmpty())
			return "redirect:/car/errors/plateError";
		try {
			carService.deleteCar(plate);
			return "redirect:/car/carsRegistered";
		} catch (Exception e) {
			return "redirect:/car/errors/plateError";
		}
	}

}
