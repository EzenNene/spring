package com.example.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.example.constant.Role;
import com.example.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegistDto {

	private Long id;

	private String loginId;

	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	private String name;

	@NotBlank(message = "본인 프로필 이미지는 필수 입력 값입니다.")
	private String photo;

	private String intru;

	private String tel;
	
	private String email;

	private String loc;

	private String instaSns;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Member createItem() {
		return modelMapper.map(this, Member.class);
	}
	
	public static MemberFormDto of(Member member) {
		return modelMapper.map(member, MemberFormDto.class);
	}
	
}
