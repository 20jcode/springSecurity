package com.ex.lab.member.service;

import com.ex.lab.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class BaseMemberService implements MemberService{

	private final MemberRepository memberRepository;

	@Override
	public void signUp () {

	}

	@Override
	public void singIn () {

	}

	@Override
	public void tierUp () {

	}

	@Override
	public void tierDown () {

	}
}
