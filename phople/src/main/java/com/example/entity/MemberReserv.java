package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.constant.Classification;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member_reserv") // 테이블명
@Getter
@Setter
@ToString
public class MemberReserv {

	@Id
	@Column(name = "reserv_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reservId;
	
	private String reservDate;
	
	private String receiver;
	
	private String receiverTel;
	
	private String regDate;
	
	private String concept;
	
	private String bigImg;
	
	@Enumerated(EnumType.STRING)
	private Classification classification;
	

	
}
