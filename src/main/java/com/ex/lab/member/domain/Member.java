package com.ex.lab.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member implements UserDetails {

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities () {
		List<SimpleGrantedAuthority> list = new ArrayList<>();

		list.add(new SimpleGrantedAuthority(tier.toString()));

		return list;
	}

	@Override
	public String getUsername () {
		return this.email; //TODO : 수정필요
	}

	@Override
	public boolean isAccountNonExpired () {
		return true;
	}

	@Override
	public boolean isAccountNonLocked () {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired () {
		return true;
	}

	@Override
	public boolean isEnabled () {
		return true;
	}
}
