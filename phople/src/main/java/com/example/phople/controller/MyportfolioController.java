package com.example.phople.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myportfolio")
public class MyportfolioController {

	@GetMapping(value = "/myportfolio")
	public String myportfolio() {
		return "portfolio/ppportfolio";
	}
	
}
