package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Car;
import com.example.demo.model.CarRegister;
import com.example.demo.model.User;
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
	public String register(HttpServletRequest request, HttpServletResponse response, String cpf, Model model) {
		CarRegister car;
		if(cpf == null){
			car = new CarRegister();
		}else{
			car = new CarRegister(cpf, new Car());
		}
		model.addAttribute("car", car);
		return "car/carsRegister";
	}

	@PostMapping(value = "/register")
	public String createNewCar(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("carRegister") CarRegister carRegister) {
		
			Car car = carRegister.getCar();
			String cpf = carRegister.getOwnerCpf();

			carService.saveCar(car, cpf);

			return "redirect:/car/list";
			
	}

	@GetMapping(value = "/list")
	public String listCars(Model model) {
		List<Car> carList = carService.listCar();
		model.addAttribute("cars", carList);
		return "car/carsRegistered";
	}

	@GetMapping(value = "/{plate}")
	public String getCar(@PathVariable String plate, Model model) {
		Car carTmp = carService.getCar(plate);
		model.addAttribute("car", carTmp);
		return "car/singleCarRegistered";

	}

	@GetMapping(value = "edit/{plate}")
	public String editCarView(Car car, Model model){
		Car carTmp = carService.getCar(car.getPlate());
		model.addAttribute("car", carTmp);
		return "car/carEditing";
	}

	@PostMapping(value = "/edit/{plate}")
	public String editCar(@ModelAttribute("car") Car car, @PathVariable("plate") String plate) {
		User user = carService.getCar(plate).getOwner();
		car.setOwner(user);
		carService.editCar(car, plate);
		return "redirect:/car/list";
	}

	@GetMapping(value = "/delete/{plate}")
	public String deleteCar(@PathVariable("plate") String plate) {
		carService.deleteCar(plate);
		return "redirect:/car/list";

	}

}
