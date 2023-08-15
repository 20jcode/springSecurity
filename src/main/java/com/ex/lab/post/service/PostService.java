package com.ex.lab.post.service;


import com.ex.lab.post.dto.PostRequestDto;
import com.ex.lab.post.dto.PostResponseDto;

public interface PostService {
	long create(PostRequestDto dto);

	void update(PostRequestDto dto);

	void del(Long postId);

	PostResponseDto read(Long postId);

}
