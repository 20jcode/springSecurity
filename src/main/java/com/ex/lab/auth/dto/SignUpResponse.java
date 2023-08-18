package com.ex.lab.auth.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class SignUpResponse {

	private String userName;

	private String jwt;

	@Builder
	public SignUpResponse (String userName, String jwt) {
		this.userName = userName;
		this.jwt = jwt;
	}
}
