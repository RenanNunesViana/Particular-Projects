package com.example.demo.controllers;

import com.example.demo.model.Order;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	public String createNewOrder(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("order") Order order) {
		orderService.createOrder(order);
		return "redirect:/order/list";
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
		Order order = orderService.getOrder(id);
		model.addAttribute("order", order);
		return "order/singleOrderRegistered";

	}

	@GetMapping
	public String getOrderByCustomerCpf(String cpf, Model model) {
		List<Order> orders = orderService.getOrderByCustCpf(cpf);
		model.addAttribute("orders", orders);
		return "order/ordCustRegistered";
	}

	@GetMapping(value = "/carplate/{carPlate}")
	public String getOrderByCarPlate(@PathVariable("carPlate") String plate, Model model) {

		List<Order> orders = (orderService.getOrderByCar(plate));
		model.addAttribute("orders", orders);
		return "order/ordersRegistered";
	}

	@PutMapping(value = "{id}")
	public String editOrder(@PathVariable("id") Long id, @ModelAttribute("order") Order order) {
		orderService.editOrder(order.getId(), order);
		return "order/orderRegistered";
	}

	@DeleteMapping
	public String deletingOrder(Long id) {
		orderService.deleteOrder(id);
		return "order/orderRegistered";
	}
}
