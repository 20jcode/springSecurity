package com.ex.lab.security;

import com.ex.lab.member.domain.Member;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class AccountSecurityAdapter extends Member {
	private Member member;

	public AccountSecurityAdapter(Member member){
		super(member.getName(),member.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
		this.member = member;
	}
}
