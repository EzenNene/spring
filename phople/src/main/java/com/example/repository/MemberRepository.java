package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.MainMemberDto;
import com.example.dto.MemberSearchDto;
import com.example.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findByLoginId(String loginId); //회원가입시 중복 회원이 있는지 검사하기 위해

	//메인화면에 뿌리는 멤버목록을 가져온다.
		Page<MainMemberDto> getMainMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);
	
}
