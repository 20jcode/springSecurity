package com.ex.lab.member.service;

import com.ex.lab.member.domain.Member;
import com.ex.lab.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {

		return memberRepository.findMemberByEmail(email)
				.orElseThrow(()-> new IllegalArgumentException("멤버조회실패"));

	}
}
