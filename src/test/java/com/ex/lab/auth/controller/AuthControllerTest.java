package com.ex.lab.auth.controller;

import com.ex.lab.auth.dto.SignUpForm;
import com.ex.lab.auth.dto.SignUpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("회원가입 과정 test")
	void signUp(){


		//given
		String memberName = "tuna";
		String password = "12345678123";
		String email = "tuna@gmail.com";


		SignUpForm signUpForm = SignUpForm.builder()
				.memberName(memberName)
				.password(password)
				.email(email)
				.build();

		ResponseEntity<SignUpResponse> response =
				testRestTemplate.postForEntity("/auth/signup",signUpForm, SignUpResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}
}