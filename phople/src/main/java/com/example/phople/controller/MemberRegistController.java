package com.example.phople.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MemberRegistController {
	
	@GetMapping(value = "/")
	public String main(Model model) {
		System.out.println("멤버 정보 등록 컨트롤러");
	
		return "inforegist/infoRegist";
	}
	
}
