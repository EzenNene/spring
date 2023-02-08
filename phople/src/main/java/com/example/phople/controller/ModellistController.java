package com.example.phople.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/modellist")
public class ModellistController {

	@GetMapping(value = "/list")
	public String modellist() {
		System.out.println("모델리스트 컨트롤러");
		return "model_list/modelList";
	}
	
}
