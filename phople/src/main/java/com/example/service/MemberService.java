package com.example.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.MainMemberDto;
import com.example.dto.MemberFormDto;
import com.example.dto.MemberImgDto;
import com.example.dto.MemberSearchDto;
import com.example.entity.Member;
import com.example.entity.MemberImg;
import com.example.repository.MemberImgRepository;
import com.example.repository.MemberRepository;


import lombok.RequiredArgsConstructor;

@Service //service 클래스의 역할
@RequiredArgsConstructor
@Transactional //서비스 클래서에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다. 
public class MemberService implements UserDetailsService { //UserDetailsService: 로그인시 request에서 넘어온 사용자 정보를 받음
	
	private final MemberRepository memberRepository; //의존성 주입
	private final MemberImgService memberImgService;
	private final MemberImgRepository memberImgRepository;
	
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
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member); //member 테이블에 insert
	}
	

	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByLoginId(member.getLoginId());
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
	
	
//	=====================================================================================
	
	// 멤버 등록
		public Long saveMember(MemberFormDto memberFormDto, List<MultipartFile> memberImgFileList) throws Exception {
			
			// 멤버 등록
			Member member = memberFormDto.createMember();
			memberRepository.save(member);
			
			//이미지 등록
			for(int i=0; i<memberImgFileList.size(); i++) {
				MemberImg memberImg = new MemberImg();
				memberImg.setMember(member);
				
				//첫번째 이미지 일때 대표 상품 이미지 여부 지정
				if(i == 0) { 
					memberImg.setRepimgYn("Y");
				} else {
					memberImg.setRepimgYn("N");
				}
				
				memberImgService.saveMemberImg(memberImg, memberImgFileList.get(i));
			}
			
				return member.getId();
		}
	
		// 멤버 가져오기
		@Transactional(readOnly = true) //트랜잭션 읽기 전용(변경감지 수행하지 않음) -> 성능향상
		public MemberFormDto getMemberDtl(Long memberId) {
			//1. member_img테이블의 이미지를 가져온다.
			List<MemberImg> memberImgList = memberImgRepository.findByMemberIdOrderByIdAsc(memberId);
			List<MemberImgDto> memberImgDtoList = new ArrayList<>();
			
			//엔티티 객체 -> dto객체로 변환
			for(MemberImg memberImg : memberImgList) {
				MemberImgDto memberImgDto = MemberImgDto.of(memberImg);
				memberImgDtoList.add(memberImgDto);
			}
			
			//2. member 테이블에 있는 데이터를 가져온다.
			Member member = memberRepository.findById(memberId)
					                  .orElseThrow(EntityNotFoundException::new);
			
			//엔티티 객체 -> dto객체로 변환
			MemberFormDto memberFormDto = MemberFormDto.of(member);
			
			//상품의 이미지 정보를 넣어준다.
			memberFormDto.setMemberImgDtoList(memberImgDtoList);
			
			return memberFormDto;
		}
		
		// 멤버 수정
		public Long updateMember(MemberFormDto memberFormDto, List<MultipartFile> memberImgFileList) throws Exception {
			
			Member member = memberRepository.findById(memberFormDto.getId())
					.orElseThrow(EntityNotFoundException::new);
			
			member.updateMember(memberFormDto);
			
			List<Long> memberImgIds = memberFormDto.getMemberImgIds(); //상품 이미지 아이디 리스트를 조회
			
			for(int i=0; i<memberImgFileList.size(); i++) {
				memberImgService.updateMemberImg(memberImgIds.get(i), memberImgFileList.get(i));
			}
			
			return member.getId();
			
		}
		
//	========================================================================================
		
		//멤버 리스트 가져오기
		@Transactional(readOnly = true)
		public Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable) {
			return memberRepository.getAdminMemberPage(memberSearchDto, pageable);
		}
		
		@Transactional(readOnly = true)
		public Page<MainMemberDto> getMainMemberPage(MemberSearchDto memberSearchDto, Pageable pageable) {
			return memberRepository.getMainMemberPage(memberSearchDto, pageable);
		}

}