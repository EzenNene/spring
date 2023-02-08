package com.myshop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("select o from Order o where o.member.email = :email order by o.orderDate desc") // 파라미터 가져온 email 쓸 때 :
	List<Order> findOrders(@Param("email")String email, Pageable pageable); 
	// 현재 로그인한 사용자의 주문목록 페이징 조건에 맞게 조회하는 추상 메서드
	
	@Query("select count(o) from Order o where o.member.email = :email")
	Long countOrder(@Param("email") String email); // 현재 로그인한 회원의 주문 개수가 몇개인지 조회
}
