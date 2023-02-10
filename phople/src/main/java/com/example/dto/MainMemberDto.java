package com.example.dto;

import com.example.constant.Role;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainMemberDto {

	private Long id;

	private String loc;
	
	private String name;

	private String intru;
	
	private String photo;
	
	private Role role;
	
	@QueryProjection //쿼리dsl로 결과 조회시 MainMemberDto 객체로 바로 받아올 수 있다.
	public MainMemberDto(Long id, String loc, String name, String photo, String intru, Role role) {
		this.id = id;
		this.loc = loc;
		this.name = name;
		this.photo = photo;
		this.intru = intru;
		this.role = role;
	}
	
}
