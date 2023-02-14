package com.example.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.example.constant.Role;
import com.example.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	
	private Role role; // 모델 or 작가
	
	private String name; // 이름
	
	private String photo; // 사진경로
	
	private String intru; // 소개
	
	private String tel; // 번호
	
	private String email; // 이메일

	private String loc; // 지역

	private String instaSns; // 인스타주소
	
	private String loginId; // 로그인아이디
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static MemberDto of(Member member) {
		return modelMapper.map(member, MemberDto.class);
	}
	
}
