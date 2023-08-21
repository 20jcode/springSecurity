package com.ex.lab.post.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private Long id;

	@Column(name = "post_name")
	private String name;

	@Column(name = "post_body")
	private String body;

	@Builder
	public Post (String name, String body) {
		this.name = name;
		this.body = body;
	}

	public void updatePostName(String name){
		this.name = name;
	}

	public void updatePostBody(String body){
		this.body = body;
	}
}
