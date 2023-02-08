package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.constant.MemberClass;
import com.example.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order {

	@Id
	@Column(name="order_id")
	private Long id;
	
	@Column
	private Long memberId;
	
	@Column
	private Date orderDate;
	
	@Column
	private OrderStatus status;
}
