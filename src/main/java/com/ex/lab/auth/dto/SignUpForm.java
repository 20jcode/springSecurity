package com.ex.lab.auth.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //사용을 위한 getter
@EqualsAndHashCode //비교를 위한
@NoArgsConstructor
public class SignUpForm {


	private String memberName;

	private String password; //TODO : 비밀번호 짫으면 안되기

	private String email;

	@Builder
	private SignUpForm (String memberName, String password, String email) {
		this.memberName = memberName;
		this.password = password;
		this.email = email;
	}
}
