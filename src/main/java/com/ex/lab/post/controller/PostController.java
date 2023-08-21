package com.ex.lab.post.controller;

import com.ex.lab.member.domain.Member;
import com.ex.lab.post.dto.PostRequestDto;
import com.ex.lab.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class PostController {

	private final PostService postService;

	@PostMapping("/test/post")
	public Long createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal Member member){
		return postService.create(requestDto);
	}
}
