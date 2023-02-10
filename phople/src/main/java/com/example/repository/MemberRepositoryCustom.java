package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dto.MainMemberDto;
import com.example.dto.MemberSearchDto;
import com.example.entity.Member;


public interface MemberRepositoryCustom {

	//상품관리 페이지 아이템을 가져온다.
	Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);
	
	//메인화면에 뿌리는 아이템을 가져온다.
	Page<MainMemberDto> getMainMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);
	
}
