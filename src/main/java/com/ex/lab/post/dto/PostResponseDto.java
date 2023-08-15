package com.ex.lab.post.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class PostResponseDto {

	private final Long id;

	private final String name;

	private final String body;

	@Builder
	private PostResponseDto (Long id, String name, String body) {
		this.id = id;
		this.name = name;
		this.body = body;
	}
}
