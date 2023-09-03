package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@GetMapping(value = "/register")
	public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
		Order order = new Order();
		model.addAttribute("order", order);
		return "order/ordersRegister";
	}

	@PostMapping(value = "/register")
	public String createNewUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("order") Order order) {

		try {

			Optional<Order> newOrder = orderService.createOrder(order);
			if (newOrder.isEmpty())
				return "redirect:/register?error";

			
			return "redirect:/order/list";

		} catch (Exception e) {
			return "redirect:/register?error";
		}

	}

	@GetMapping(value = "/filterdate")
	public String findByDateBetween(String beg, String end, Model model) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = format.parse(beg);
		Date date2 = format.parse(end);
		List<Order> filteredOrders = orderService.getOrderByDate(date1, date2);
		model.addAttribute("filteredOrders", filteredOrders);
		return "order/ordersFiltered";
	}

	@GetMapping(value = "/list")
	public String findAll(Model model) {
		List<Order> orders = orderService.listOrders();
		model.addAttribute("orders", orders);
		return "order/ordersRegistered";
	}

	@GetMapping(value = "/{id}")
	public String getOrder(@PathVariable("id") Long id, Model model) {

		try {
			Optional<Order> order = orderService.getOrder(id);
			model.addAttribute("order", order.get());
			return "order/singleOrderRegistered";
		} catch (Exception e) {
			return "redirect:/order/errors/id404Error";
		}
	}

	@GetMapping
	public String getOrderByCustomerCpf(String cpf, Model model) {		
		try {
			User user = userService.getByCpf(cpf);
			List<Order> orders = orderService.getOrderByCustCpf(cpf);
			model.addAttribute("orders", orders);
			return "order/ordCustRegistered";
		} catch (Exception e) {
			return "redirect:/order/Errors/id404Error";
		}
	}

	@GetMapping(value = "/carplate/{carPlate}")
	public String getOrderByCarPlate(@PathVariable("carPlate") String plate, Model model) {
		try {
		List<Order> orders = (orderService.getOrderByCar(plate));
		model.addAttribute("orders", orders);
		return "order/ordersRegistered";
		}catch(Exception e) {
			return "redirect:/order/Errors/id404Error";
		}
	}

	@PutMapping(value = "{id}")
	public String editOrder(@PathVariable("id") Long id, @ModelAttribute("order") Order order) {
		Optional<Order> orderTmp = orderService.getOrder(id);
		if(orderTmp.isEmpty())
			return "redirect:/order/errors/id404Error";
		try {
		orderService.editOrder(orderTmp.get().getId(), order);
		return "order/orderRegistered";
		}catch(Exception e) {
			return "redirect:/order/errors/id404Error";
		}
	}

	@DeleteMapping
	public String deletingOrder(Long id) {
		Optional<Order> order = orderService.getOrder(id);
		if(order.isEmpty())
			return "redirect;/order/errors/id404Error";
		try {
			orderService.deleteOrder(id);
			return "order/orderRegistered";
		}catch(Exception e) {
			return "redirect;/order/errors/id404Error";
		}
	}
}
