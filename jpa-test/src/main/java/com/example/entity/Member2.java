package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.constant.MemberClass;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member2 {

	@Id // primary key 설정
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String city;
	
	@Column
	private String street;
	
	@Column
	private String zipcode;
}
