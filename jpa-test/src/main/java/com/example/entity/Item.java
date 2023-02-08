package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.constant.MemberClass;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

	@Id
	@Column(name="item_id")
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private int price;
	
	@Column
	private int stockQuantity;
	
}
