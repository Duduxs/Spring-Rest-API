package com.edudev.workshop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edudev.workshop.domain.Post;
import com.edudev.workshop.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	PostService postService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post p1 = postService.findById(id);
		return ResponseEntity.ok().body(p1);
	}
}