package com.example.phople.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ModellistController {

	@GetMapping(value = "/modellist")
	public String modellist() {
		return "model_list/modelList";
	}
	
}
