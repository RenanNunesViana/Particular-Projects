package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
import com.example.demo.model.User;
import com.example.demo.services.CarService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CarService carService;
	
	
	@GetMapping(value = "/register")
	public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "customer/usersRegister";
	}

	@PostMapping(value = "/register")
	public String createNewUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {

		try {

			user.setRole("USER");

			userService.createUser(user);
			Optional<User> newUser =userService.getByCpf(user.getCpf());
			if (newUser.isEmpty()) {
				return "redirect:/register?error";
			}

			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getCpf(), user.getPassword()));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = request.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

			return "redirect:/";

		} catch (Exception e) {
			return "redirect:/register?error";
		}

	}
	
	@GetMapping(value = "/list")
	public String listCustomers(Model model){
		List<User> customerList = userService.listUsers(); 
		model.addAttribute("customers", customerList);
		return "customer/customersRegistered";
	}
	
	@GetMapping(value = "/list/{id}")
	public String getCustomer(@PathVariable("id") Long id, Model model) throws Exception {
		model.addAttribute("id",id);
		try {
		User customer = userService.getById(id).get();
		model.addAttribute("customer", customer);
		return "customer/singleCustomerRegistered";
		}catch(Exception e) {
			return "customer/errors/idError";
		}
	}
	
	@PutMapping(value = "/editing/{id}")
	public String editingCustomer(@PathVariable("id") Long id, @RequestBody User customer, Model model){
		model.addAttribute("id", id);
		try {
		User editedCustomer = userService.getByCpf(customer.getCpf()).get();
		
		if(editedCustomer == null)
			return "customer/errors/idError";
		
		editedCustomer = userService.createUser(customer);
		return "redirect:/customer/customerRegistered";
		
		}catch(Exception e) {
			return "customer/errors/idError";
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public String rmvCustomer(@PathVariable("id") Long id, Model model){
		model.addAttribute("id", id);
		try {
		Optional<User> user = userService.getById(id);
		if(user.isEmpty())
			return "redirect:/customer/errors/idError";
		userService.deleteUser(id);
		return "redirect:/customer/customerRegistered";
		}catch (Exception e) {
			return "redirect:/customer/errors/idError";
		}
	}
	
}
