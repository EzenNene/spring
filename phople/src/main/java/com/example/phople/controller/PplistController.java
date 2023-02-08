package com.example.phople.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pplist")
public class PplistController {

	@GetMapping(value = "/list")
	public String pplist() {
		return "pp_list/ppList";
	}
	
}
