package com.example.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.constant.Role;
import com.example.dto.MemberFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member") // 테이블명
@Getter
@Setter
@ToString
public class Member extends BaseEntity implements UserDetails{

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long memberId;

	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String name;

	private String photo;
	
	private String intru;
	
	private String tel;
	
	@Column(unique = true)
	private String email;
	
	private String loc;
	
	private String instaSns;
	
	private String loginId;

	private String password;

	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();

		member.setName(memberFormDto.getName());
		member.setPhoto(memberFormDto.getPhoto());
		member.setIntru(memberFormDto.getIntru());
		member.setTel(memberFormDto.getTel());
		member.setEmail(memberFormDto.getEmail());
		member.setLoc(memberFormDto.getLoc());
		member.setInstaSns(memberFormDto.getInstaSns());
		member.setLoginId(memberFormDto.getLoginId());

		String password = passwordEncoder.encode(memberFormDto.getPassword());
		member.setPassword(password);
		
		member.setRole(Role.PP);

		return member;
	}
	
	public void updateMember(MemberFormDto memberFormDto) {
		this.name = memberFormDto.getName();
		this.photo = memberFormDto.getPhoto();
		this.intru = memberFormDto.getIntru();
		this.tel = memberFormDto.getTel();
		this.instaSns = memberFormDto.getInstaSns();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(role.toString()));
		auth.add(new SimpleGrantedAuthority(name.toString()));
		return auth;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
