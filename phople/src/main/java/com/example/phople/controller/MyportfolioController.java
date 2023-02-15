package com.example.phople.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		// Principal : ID 정보 가져다 사용 가능
		String id = principal.getName();
		
		MemberDto memberDto = memberService.findUserDetail(id);
		System.out.println("================="+memberDto.getName());
		model.addAttribute("memberDto" , memberDto);
		return "portfolio/ppportfolio";
	}
	
	@GetMapping(value = "/portfolioEdit")
	public String myportfolioEditPage(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model, Principal principal) {
		// Principal : ID 정보 가져다 사용 가능
		String id = principal.getName();
			model.addAttribute("memberFormDto", new MemberFormDto());
		MemberDto memberDto = memberService.findUserDetail(id);
		System.out.println("================="+memberDto.getName());
		model.addAttribute("memberDto", memberDto);
		return "portfolio/infoRegist";
	}
	
	// 마이포폴에서 정보수정
	@PostMapping(value = "/portfolioEdit")
	public String myportfolioEdit(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model, Principal principal) {
		// Principal : ID 정보 가져다 사용 가능
		String id = principal.getName();
				
		MemberDto memberDto = memberService.findUserDetail(id);
		System.out.println("================="+memberDto.getName());
		model.addAttribute("memberDto", memberDto);
		
		try {
			memberService.myportfolioEdit(memberFormDto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "포트폴리오 수정 중 에러가 발생하였습니다.");
			return "portfolio/infoRegist";
		}
		
		return "redirect:/";
	}
}
