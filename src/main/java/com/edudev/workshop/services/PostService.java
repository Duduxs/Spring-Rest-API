package com.edudev.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudev.workshop.domain.Post;
import com.edudev.workshop.repository.PostRepository;
import com.edudev.workshop.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> p1 = postRepository.findById(id);
		
		return p1.orElseThrow(() -> new ObjectNotFoundException("Id not exists"));
	}
	
	public List<Post> findByTitle(String title){
		return postRepository.findByTitle(title);
	}
	
	public List<Post> fullSearch(String text){
		return postRepository.fullSearch(text);
	}
}
