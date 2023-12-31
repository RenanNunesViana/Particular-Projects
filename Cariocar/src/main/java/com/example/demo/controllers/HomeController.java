package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class HomeController {

    @ResponseBody
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String index(){
        return "hello world";
    }

    @ResponseBody
    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public String about(){
        return "about";
    }
    
    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String text() {
    	return "this is a free page to navagate";
    }
    
    @GetMapping(value = "/error")
    public void error() {
    	throw new IllegalArgumentException("testing error");
    }
    
}