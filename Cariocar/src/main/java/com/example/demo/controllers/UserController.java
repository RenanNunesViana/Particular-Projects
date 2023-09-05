package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

		user.setRole("USER");

		userService.createUser(user);

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getCpf(), user.getPassword()));
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		HttpSession session = request.getSession(true);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

		return "redirect:/";

	}

	@GetMapping(value = "/list")
	public String listCustomers(Model model) {
		List<User> customerList = userService.listUsers();
		model.addAttribute("customers", customerList);
		return "customer/customersRegistered";
	}

	@GetMapping(value = "/list/{id}")
	public String getCustomer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		User customer = userService.getById(id);
		model.addAttribute("customer", customer);
		return "customer/singleCustomerRegistered";

	}

	@PutMapping(value = "/editing/{id}")
	public String editingCustomer(@PathVariable("id") Long id, @RequestBody User user, Model model) {
		model.addAttribute("id", id);
		userService.editUser(user);
		return "redirect:/customer/customerRegistered";
	}

	@DeleteMapping(value = "/delete/{id}")
	public String rmvCustomer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		userService.deleteUser(id);
		return "redirect:/customer/customerRegistered";
	}

}
