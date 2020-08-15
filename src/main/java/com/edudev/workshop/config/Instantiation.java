package com.edudev.workshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.edudev.workshop.domain.Post;
import com.edudev.workshop.domain.User;
import com.edudev.workshop.dto.AuthorDTO;
import com.edudev.workshop.dto.CommentDTO;
import com.edudev.workshop.repository.PostRepository;
import com.edudev.workshop.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null,"Eduardo José", "duduxss3@gmail.com");
		User u2 = new User(null, "Douglas Orlando", "Doug45@gmail.com");
		User u3 = new User(null, "Jonas Silvestre", "Jo_eSilva@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1,u2,u3));
		
	Post p1 = new Post(null, sdf.parse("21/03/2020"), "Comprei um novo cel ;)" , "Última geração, mt bom!", new AuthorDTO(u1) );
	Post p2 = new Post(null, sdf.parse("21/03/2020"), "Uma nova jornada" , "Hoje, espero começar uma nova jornada na minha vida.",new AuthorDTO(u1));
		
		postRepository.saveAll(Arrays.asList(p1,p2));
		
		CommentDTO c1 = new CommentDTO("Caramba, que massa! Quanto foi?!", sdf.parse("21/03/2020"), new AuthorDTO(u3));
		CommentDTO c2 = new CommentDTO("Ahh, ainda vou comprar o meu x-x", sdf.parse("22/03/2020"), new AuthorDTO(u2));
		CommentDTO c3 = new CommentDTO("Be Positive!", sdf.parse("22/03/2020"), new AuthorDTO(u3));
		p1.getComments().addAll(Arrays.asList(c1,c2));
		p2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(p1,p2));

		
		u1.getPosts().addAll(Arrays.asList(p1,p2));
		userRepository.save(u1);
	
		
		
	}

}
