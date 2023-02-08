package com.example.phople.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	@GetMapping(value = "/")
	public String main() {
		System.out.println("메인 컨트롤러");
		return "main";
	}
	
	@GetMapping(value = "/main")
	public String main2() {
		System.out.println("메인 컨트롤러2");
		return "main";
	}
	
//	@GetMapping(value = "/main")
//	public String photographerlist() {
//		return "main";
//	}
//	
//	@GetMapping(value = "/modellist")
//	public String modellist() {
//		return "model_list/modellist";
//	}
//	
//	@GetMapping(value = "/ppportfolio")
//	public String myportfolio() {
//		return "portfolio/ppportfolio";
//	}
//	
//	@GetMapping(value = "/ppreservation")
//	public String myreservation() {
//		return "reservation/ppreservation";
//	}
//	
//	@GetMapping(value = "/signup")
//	public String signup() {
//		return "signup/signup";
//	}
//	
//	@GetMapping(value = "/login")
//	public String login() {
//		return "login/login";
//	}
	
}
