package com.example.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.modelmapper.ModelMapper;

import com.example.constant.Role;
import com.example.entity.Member;
//import com.wss.dto.BroadFormDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelDto {

	private Long id;

	private String loginId;

	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	private String name;

	private String photo;

	private String intru;

	private String tel;
	
	private String email;

	private String loc;

	private String instaSns;
	
	public ModelDto (Member member) {
		this.photo = member.getPhoto();
		this.intru = member.getIntru();
		this.tel = member.getTel();
		this.email = member.getEmail();
		this.loc = member.getLoc();
		this.instaSns = member.getInstaSns();
	}
	
//	private BroadFormDto broadFormDto = new BroadFormDto();
	
	//매핑용.
	public static ModelMapper modelMapper = new ModelMapper();
	
	public Member createMember() {
		return modelMapper.map(this, Member.class);
	}
	
	//작가의 정보(broadFormDto)를 broadFormDtoList에 담아준다.
	/*
	 * public void addBroadFormDtoList(BroadFormDto broadFormDto){
	 * broadFormDtoList.add(broadFormDto); }
	 */
	
}
