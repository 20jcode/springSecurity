package com.ex.lab.auth.controller;

import com.ex.lab.auth.dto.SignUpForm;
import com.ex.lab.auth.dto.SignUpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {

	@PostMapping("/auth/signup")
	public SignUpResponse signUp(@Valid @RequestBody SignUpForm signUpForm,
	                             BindingResult bindingResult){


	}
}
