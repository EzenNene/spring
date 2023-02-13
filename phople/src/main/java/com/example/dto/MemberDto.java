package com.example.dto;

import java.time.LocalDateTime;

import com.example.constant.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	private Long memberId; // 멤버코드 PK
	
	private Role role; // 모델 or 작가
	
	private String name; // 이름
	
	private String photo; // 사진경로
	
	private String intru; // 소개
	
	private String tel; // 번호
	
	private String email; // 이메일

	private String loc; // 지역

	private String instaSns; // 인스타주소
	
	private String loginId; // 로그인아이디
	
	private String password; // 비밀번호
	
	
	private LocalDateTime regTime; //등록 시간

	private LocalDateTime updateTime; //수정 시간
}
