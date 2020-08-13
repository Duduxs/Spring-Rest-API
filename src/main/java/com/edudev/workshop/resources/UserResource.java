package com.edudev.workshop.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edudev.workshop.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = new ArrayList<>();
		User u1 = new User("1","Eduardo José", "duduxss3@gmail.com");
		User u2 = new User("2","Doug", "doug@hotmail.com");
		list.addAll(Arrays.asList(u1,u2));
		return ResponseEntity.ok().body(list);
	}
	
	
}
