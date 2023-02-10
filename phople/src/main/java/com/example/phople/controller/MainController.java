package com.example.phople.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.dto.MainMemberDto;
import com.example.dto.MemberSearchDto;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MemberService memberService;
	
	@GetMapping(value = "/")
	public String main(MemberSearchDto memberSearchDto, Optional<Integer> page, Model model) {
		System.out.println("메인 컨트롤러");
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<MainMemberDto> members = memberService.getMainMemberPage(memberSearchDto, pageable);
		
		model.addAttribute("members", members);
		model.addAttribute("memberSearchDto", memberSearchDto);
		model.addAttribute("maxPage", 5);
		
		return "main";
	}
	
}
