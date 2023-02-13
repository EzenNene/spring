package com.example.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.constant.Role;
import com.example.dto.MainMemberDto;
import com.example.dto.MemberDto;
import com.example.dto.PpDto;
import com.example.entity.Member;
import com.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service //service 클래스의 역할
@RequiredArgsConstructor
@Transactional //서비스 클래서에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다. 
public class MemberService implements UserDetailsService { //UserDetailsService: 로그인시 request에서 넘어온 사용자 정보를 받음
	
	private final MemberRepository memberRepository; //의존성 주입
	
	// 로그인
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		Member member = memberRepository.findByLoginId(loginId); 
		
		if(member == null) {
			throw new UsernameNotFoundException(loginId);
		}
		
		//userDetails의 객체를 반환
		return User.builder()
				.username(member.getLoginId())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}
	
	// 이메일 중복체크 메소드
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByLoginId(member.getLoginId());
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
	
	// 회원가입 테이블에 insert
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member); //member 테이블에 insert
	}
	
	public List<PpDto> getMemberBroad(Role role) {
//		return memberRepository.findByRole(role);
		List<Member> MemberList = memberRepository.findByRole(role); //컨트롤러에서 getBroad()사용시 List<Member>를 broad라는 이름으로 다 가져옴.
		List<MemberDto> memberDtoList = new ArrayList<>();
		
		for(Member member : MemberList) {
			
			MemberDto memberDto = new MemberDto(member);
			
			memberDtoList.add(memberDto);
		}
		
		return memberDtoList;
	}
	
//	=====================================================================================
	
	// 멤버 등록

		
	// 멤버 수정

//	========================================================================================
		
		public List<Member> getMainPpList(Role role) {
			return memberRepository.findByRole(role);
		}
}