package com.ex.lab.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_id")
	private Long id;

	@Column(name = "member_name")
	private String name;

	@Column(name = "member_email",unique = true,length = 45) //유니크 설정해서 인덱스화?
	private String email;

	@Column(name = "member_password")
	private String password;

	@Column(name = "member_tier")
	@Enumerated(EnumType.STRING)
	private Tier tier;

	@Builder
	public Member (String name, String email, String password, Tier tier) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.tier = tier;
	}
}
