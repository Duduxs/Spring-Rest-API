package com.edudev.workshop.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edudev.workshop.domain.Post;
import com.edudev.workshop.domain.User;
import com.edudev.workshop.dto.UserDTO;
import com.edudev.workshop.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id ){
		User user = service.findById(id);
		UserDTO userDTO = new UserDTO(user);
		
		return ResponseEntity.ok().body(userDTO);
	}
	
	@PostMapping(value="/insert")
	public ResponseEntity<Void> insert(UserDTO userDTO) {
		User user = service.fromDTO(userDTO);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<Void> delete(String id){

		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update( UserDTO userDTO, @PathVariable String id){
		User u = service.fromDTO(userDTO);
		u.setId(id);
		u = service.update(u);
	
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/posts/{id}")
	public ResponseEntity<List<Post>> userPosts(@PathVariable String id){
		User u1 = service.findById(id);
		return ResponseEntity.ok().body(u1.getPosts());
	}
	
	
}
