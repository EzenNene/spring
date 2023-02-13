package com.example.phople.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.constant.Role;
import com.example.dto.MainMemberDto;
import com.example.entity.Member;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MemberService memberService;
	
	@GetMapping(value = "/")
	public String main(Model model) {
		System.out.println("메인 컨트롤러");
		
		Role pp = Role.PP;
		
		List<Member> members = memberService.getMainPpList(pp);
		model.addAttribute("members", members);
		
		return "main";
	}
	
}
