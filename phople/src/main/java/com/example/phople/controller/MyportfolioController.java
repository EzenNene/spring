package com.example.phople.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.MemberDto;
import com.example.dto.MemberFormDto;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myportfolio")
public class MyportfolioController {

	private final MemberService memberService;

	
	@GetMapping(value = "/portfolio")
	public String myportfolio(Model model, Principal principal) {
		String id = principal.getName();
		
		MemberDto memberDto = memberService.findUserDetail(id);
		System.out.println("================="+memberDto.getName());
		model.addAttribute("memberDto" , memberDto);
		return "portfolio/ppportfolio";
	}
	
	@GetMapping(value = "/portfolioEdit")
	public String myportfolioEdit(Model model) {
		model.addAttribute("MemberFormDto", new MemberFormDto());
		return "portfolio/infoRegist";
	}
}
