package com.myshop.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myshop.dto.MemberFormDto;
import com.myshop.entity.Member;
import com.myshop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	/*Controller의 메서드는 Model이라는 타입의 객체를 파라미터로 받을 수 있다.
	순수하게 JSP Servlet으로 웹 어플리케이션을 만들 때 보통 request나 session 
	내장객체에 정보를 담아 jsp에 넘겨주곤 했는데 Spring에서는 Model이라는 녀석을 쓴다.
	즉 request.setAttribute() 와 비슷한 역할을 하는 것.*/
	
	//회원가입 화면 
	@GetMapping(value = "/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}
	
	//회원가입 버튼을 눌렀을때 실행되는 메소드
	@PostMapping(value = "/new")
	public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		//@Valid : 유효성을 검증하려는 객체 앞에 붙인다.
		//bindingResult: 유효성 검증후에 결과를 넣어준다.
		
		//에러가 있다면 회원가입 페이지로 이동
		if(bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		
		try {			
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}
		
		return "redirect:/";
	}
	
	//로그인 화면 
	@GetMapping(value = "/login")
	public String loginMember() {
		return "member/memberLoginForm";
	}
	
	private final SessionManager sessionManager;
	
	/*
	//쿠키, 세션 테스트
	@PostMapping(value = "/login2")
	public String loginMember2(HttpServletResponse response, HttpSession session, @RequestParam String email) {
		System.out.println("email: " + email);
		Cookie idCookie = new Cookie("userCookieId2", email);
		response.addCookie(idCookie);
		
		session.setAttribute("useSessionId2", email);
		
		sessionManager.createSession(email, response);
		
		return "member/memberLoginForm";
	}
	*/
	
	//로그인을 실패했을때
	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "member/memberLoginForm";
	}
}
