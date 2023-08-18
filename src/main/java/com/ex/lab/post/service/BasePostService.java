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

	private final PostRepository postRepository;

	@Override
	public long create (PostRequestDto dto) {

		return postRepository.save(Post.builder()
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
		Post post = postRepository.getReferenceById(postId);

		return PostResponseDto.builder()
				.body(post.getBody())
				.build();
	}
}
