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
	
//	원래 수정할 때 넣는건데 가입 시 넣음 ====================================

	private String photo;
	
	private String intru;
	
	private String instaSns;
	
	
	private List<MemberImgDto> memberImgDtoList = new ArrayList<>(); // 멤버 이미지 정보를 저장하는 리스트
	
	private List<Long> memberImgIds = new ArrayList<>(); // 멤버의 이미지 아이디를 저장 -> 수정시에 이미지 아이디를 담아 둘 용도.
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Member createMember() {
		return modelMapper.map(this, Member.class);
	}
	
	public static MemberFormDto of(Member member) {
		return modelMapper.map(member, MemberFormDto.class);
	}
	
}
