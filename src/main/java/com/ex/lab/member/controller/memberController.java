package com.ex.lab.member.controller;

import com.ex.lab.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class memberController {

	private final MemberService memberService;


}
