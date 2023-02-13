package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pf_des_images") // 테이블명
@Getter
@Setter
@ToString
public class PfDesImages {

	@Id
	@Column(name = "pf_lower_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pfLowerImgId;
	
	private String desImg1;
	
	private String des1;
	
}
