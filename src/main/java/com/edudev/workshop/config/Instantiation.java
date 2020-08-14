package com.edudev.workshop.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.edudev.workshop.domain.User;
import com.edudev.workshop.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User u1 = new User(null,"Eduardo José", "duduxss3@gmail.com");
		User u2 = new User(null, "Douglas Orlando", "Doug45@gmail.com");
		User u3 = new User(null, "Jonas Silvestre", "Jo_eSilva@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1,u2,u3));
		
	}

}
