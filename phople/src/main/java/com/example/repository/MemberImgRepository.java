package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.MemberImg;

public interface MemberImgRepository extends JpaRepository<MemberImg, Long> {

	List<MemberImg> findByMemberIdOrderByIdAsc(Long memberId);
	
	//상품의 대표 이미지를 찾음
	MemberImg findByMemberIdAndRepimgYn(Long memberId, String repimgYn);
	
}
