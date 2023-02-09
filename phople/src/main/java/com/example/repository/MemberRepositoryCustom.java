package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dto.MainMemberDto;
import com.example.dto.MemberSearchDto;

public interface MemberRepositoryCustom {

	//메인화면에 뿌리는 멤버목록을 가져온다.
	Page<MainMemberDto> getMainMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);
	
}
