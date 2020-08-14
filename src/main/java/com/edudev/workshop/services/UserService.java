 package com.edudev.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudev.workshop.domain.User;
import com.edudev.workshop.dto.UserDTO;
import com.edudev.workshop.repository.UserRepository;
import com.edudev.workshop.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Id not exists"));
	}
	
	public User insert(User user) {
		 return repo.insert(user);
	}

	public void delete(String id) {
		 findById(id);
		 repo.deleteById(id);
	}
	
	public User update(User user) {
		User u = findById(user.getId());
		updateData(u, user);
		return repo.save(u);	
	}
	
	private void updateData(User u, User user) {
		u.setName(user.getName());
		u.setEmail(user.getEmail());
	}
	
	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
	

}
