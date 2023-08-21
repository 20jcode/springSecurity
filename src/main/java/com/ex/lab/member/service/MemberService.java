package com.ex.lab.member.service;

import com.ex.lab.auth.dto.SignUpForm;
import com.ex.lab.auth.dto.SignUpResponse;
import com.ex.lab.member.domain.Member;
import com.ex.lab.member.domain.MemberRepository;
import com.ex.lab.member.domain.Tier;
import com.ex.lab.security.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	//private final AuthenticationManager authenticationManager;

	@Transactional
	public SignUpResponse createNewMember(SignUpForm signUpForm){

		Member createdMember = memberRepository.save(Member.builder()
				.name(signUpForm.getMemberName())
				.email(signUpForm.getEmail())
				.password(passwordEncoder.encode(signUpForm.getPassword()))
				.tier(Tier.LOW)
				.build());

		String jwt = loginEmailAndPassword(signUpForm.getEmail(),signUpForm.getPassword());

		return SignUpResponse.builder()
				.userName(createdMember.getName())
				.jwt(jwt)
				.build();
	}

	@Transactional
	public boolean emailIsPresent(String email){
		return memberRepository.findMemberByEmail(email).isPresent();
	}

	public String loginEmailAndPassword(String email, String password){
		Optional<Member> member = memberRepository.findMemberByEmail(email);

		if (member.isEmpty()){
			throw new IllegalArgumentException("이메일이 존재하지 않습니다");
		} else if(passwordEncoder.matches(password,member.get().getPassword()))  {
			throw new IllegalArgumentException("패스워드가 일치하지 않습니다");
		}

		return createJwt(member.get());
	}

	private String createJwt(Member member){
		return jwtProvider.createToken(member.getUsername(), member.getAuthorities()
				.stream().map(Object::toString).collect(Collectors.toList()));
	}

	private void getAuth(String email,String password){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,password);


	}
}
