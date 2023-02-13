package com.example.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.example.entity.Member;

import lombok.Getter;
import lombok.Setter;

//회원가입으로부터 넘어오는 가입정보를 담을DTO

@Getter
@Setter
public class MemberFormDto {
	
	private Long id; //상품코드
	
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String loginId;
	
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
	private String password;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	@NotBlank(message = "연락처는 필수 입력 값입니다.")
	private String tel;

	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	private String email;
	
	@NotBlank(message = "촬영지역은 필수 입력 값입니다.")
	private String loc;

	private String photo;
	
	private String intru;
	
	private String instaSns;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Member createMember() {
		return modelMapper.map(this, Member.class);
	}
	
	public static MemberFormDto of(Member member) {
		return modelMapper.map(member, MemberFormDto.class);
	}
	
}
