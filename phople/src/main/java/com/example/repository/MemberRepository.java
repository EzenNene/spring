package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.constant.Role;
import com.example.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>,
	QuerydslPredicateExecutor<Member>, MemberRepositoryCustom {
	
	//회원가입시 중복 회원이 있는지 검사하기 위해
	Member findByLoginId(String loginId); 
	
	//select * from item where item_nm = ?
	List<Member> findByName(String name);
	
	//select * from member where role = ?
	List<Member> findByRole(Role role);
	
}
