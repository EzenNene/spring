package com.example.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.example.entity.MemberImg;
import com.example.repository.MemberImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberImgService {

	@Value("${memberImgLocation}")
	private String memberImgLocation; //C:/shop/member
	
	private final MemberImgRepository memberImgRepository;
	
	private final FileService fileService;
	
	//이미지 저장 메소드
	public void saveMemberImg(MemberImg memberImg, MultipartFile memberImgFile) throws Exception {
		String oriImgName = memberImgFile.getOriginalFilename(); //파일 이름
		String imgName = "";
		String imgUrl = "";
		
		//파일 업로드
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(memberImgLocation, oriImgName, memberImgFile.getBytes());
			imgUrl = "/images/member/" + imgName;
		}
		
		//상품 이미지 정보 저장
		memberImg.updateMemberImg(oriImgName, imgName, imgUrl);
		memberImgRepository.save(memberImg);
		
	}
	
	//이미지 업데이트 메소드
	public void updateMemberImg(Long memberImgId, MultipartFile memberImgFile) throws Exception {
		if(!memberImgFile.isEmpty()) { //파일이 있으면
			MemberImg savedMemberImg = memberImgRepository.findById(memberImgId)
					.orElseThrow(EntityNotFoundException::new);
			
			//기존 이미지 파일 삭제
			if(!StringUtils.isEmpty(savedMemberImg.getImgName())) {
				// C:/shop/member/a110f979-1467-4c7e-8346-52373e55018d.jpg
				fileService.deleteFile(memberImgLocation + "/" + savedMemberImg.getImgName());
			}
			
			//수정된 이미지 파일 업로드
			String oriImgName = memberImgFile.getOriginalFilename(); 
			String imgName = fileService.uploadFile(memberImgLocation, oriImgName, memberImgFile.getBytes());
			String imgUrl = "/images/member/" + imgName;
			
			//★ savedMemberImg는 현재 영속상태이므로 데이터를 변경하는 것만으로 변경감지 기능이 동작하여 트랜잭션이 끝날때 update쿼리가 실행된다.
			//-> 엔티티가 반드시 영속상태여야 한다.
			savedMemberImg.updateMemberImg(oriImgName, imgName, imgUrl);
		}
	}
	
}
