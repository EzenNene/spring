package com.example.service;



import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.constant.Role;
import com.example.dto.MemberDto;
import com.example.dto.MemberFormDto;
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
		
		System.out.println(member.getLoginId());
		
		//userDetails의 객체를 반환
//		return User.builder()
//				.username(member.getLoginId())
//				.password(member.getPassword())
//				.roles(member.getRole().toString())
//				.build();
		
		return member;
		
		
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
	

//	=====================================================================================
	
	// 멤버 등록

		
	// 멤버 수정
	public Long myportfolioEdit(MemberFormDto memberFormDto) throws Exception {
		
		Member member = memberRepository.findById(memberFormDto.getId())
				.orElseThrow(EntityNotFoundException::new);
		
		member.updateMember(memberFormDto);

		return member.getMemberId();
		
	}
	

//	========================================================================================
		
	// (메인페이지) role 에 따라 전체 멤버 불러오기
		public List<Member> getMainPpList(Role role) {
			return memberRepository.findByRole(role);
		}
		
		public MemberDto findUserDetail(String id) {
			Member member = memberRepository.findByLoginId(id);
			return MemberDto.of(member);
		}
}