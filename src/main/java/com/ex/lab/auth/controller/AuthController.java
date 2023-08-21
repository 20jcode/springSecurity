package com.ex.lab.auth.controller;

import com.ex.lab.auth.dto.SignUpForm;
import com.ex.lab.auth.dto.SignUpResponse;
import com.ex.lab.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class AuthController {

	private final MemberService memberService;
	@PostMapping("/auth/signup")
	public SignUpResponse signUp(@RequestBody SignUpForm signUpForm){

		if(memberService.emailIsPresent(signUpForm.getEmail())){
			throw new IllegalArgumentException("이메일이 중복됩니다");
		}

		return memberService.createNewMember(signUpForm);
	}

	@GetMapping("/auth/signin")
	public String signIn(@RequestParam String email, @RequestParam String password) {
		return memberService.loginEmailAndPassword(email, password);
	}
}
