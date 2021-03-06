package com.edudev.workshop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edudev.workshop.domain.Post;
import com.edudev.workshop.resources.util.URL;
import com.edudev.workshop.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	PostService postService;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll(){
		List<Post> list = postService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post p1 = postService.findById(id);
		return ResponseEntity.ok().body(p1);
	}
	
	@GetMapping(value="/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text){
		text = URL.decodeParam(text);
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value="/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value="text",defaultValue="") String text){
		List<Post> list = postService.fullSearch(text);
		return ResponseEntity.ok().body(list);
	}
}
