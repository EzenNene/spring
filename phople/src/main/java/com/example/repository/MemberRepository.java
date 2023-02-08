package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findByLoginId(String loginId); //회원가입시 중복 회원이 있는지 검사하기 위해
}
