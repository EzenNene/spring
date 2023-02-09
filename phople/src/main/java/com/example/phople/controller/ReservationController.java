package com.example.phople.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myreservation")
public class ReservationController {

	@GetMapping(value = "/reservation")
	public String myportfolio() {
		return "reservation/ppreservation";
	}
	
}
