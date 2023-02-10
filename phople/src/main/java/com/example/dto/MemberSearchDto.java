package com.example.dto;

import com.example.constant.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSearchDto {

	private String searchDateType;
	private String searchBy;
	private String searchQuery = "";
	
	private Role role;
	
}
