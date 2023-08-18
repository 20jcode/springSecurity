package com.ex.lab.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
	public Optional<Member> findMemberByEmail(String email);
}
