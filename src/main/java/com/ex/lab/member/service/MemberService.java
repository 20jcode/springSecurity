package com.ex.lab.member.service;

import com.ex.lab.auth.dto.SignUpForm;
import com.ex.lab.auth.dto.SignUpResponse;
import com.ex.lab.member.domain.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	private final AuthenticationManager authenticationManager;

	@Transactional
	public SignUpResponse createNewMember(SignUpForm signUpForm){

		return null;
	}
}
