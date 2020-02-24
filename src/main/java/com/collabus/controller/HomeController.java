package com.collabus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// main pagd
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
}
