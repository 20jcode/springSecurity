package com.ex.lab.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
	public Member findMemberByEmail(String email);
}
