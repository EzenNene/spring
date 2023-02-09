package com.example.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainMemberDto {

	private Long id;
	
	private String loc;
	
	private String name;
	
	private String photo;
	
	private String intru;
	
	@QueryProjection
	public MainMemberDto(Long id, String loc, String name, String photo, String intru) {
		this.id = id;
		this.loc = loc;
		this.name = name;
		this.photo = photo;
		this.intru = intru;
	}
	
}
