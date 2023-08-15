package com.ex.lab.post.service;

import com.ex.lab.post.domain.Post;
import com.ex.lab.post.domain.PostRepository;
import com.ex.lab.post.dto.PostRequestDto;
import com.ex.lab.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasePostService implements PostService{

	private final PostRepository repo;

	@Override
	public long create (PostRequestDto dto) {

		return repo.save(Post.builder()
				.name(dto.getName())
				.body(dto.getBody())
				.build()).getId();
	}

	@Override
	public void update (PostRequestDto dto) {

	}

	@Override
	public void del (Long postId) {

	}

	@Override
	public PostResponseDto read (Long postId) {
		return null;
	}
}
