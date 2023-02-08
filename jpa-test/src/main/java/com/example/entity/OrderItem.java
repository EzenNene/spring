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
public class OrderItem {

	@Id
	@Column(name="order_item_id")
	private Long id;
	
	@Column
	private Long orderId;
	
	@Column
	private Long itemId;
	
	@Column
	private int orderPrice;
	
	@Column
	private int count;
	
}
