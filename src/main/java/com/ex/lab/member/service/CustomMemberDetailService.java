package com.ex.lab.member.service;

import com.ex.lab.member.domain.Member;
import com.ex.lab.member.domain.MemberRepository;
import com.ex.lab.security.AccountSecurityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomMemberDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

		Member member = memberRepository.findMemberByEmail(email);
		if(member==null) throw new UsernameNotFoundException("멤버 검색 실패");

		return new AccountSecurityAdapter(member);
	}
}
