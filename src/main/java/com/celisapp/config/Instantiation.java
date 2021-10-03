package com.celisapp.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.celisapp.domain.Post;
import com.celisapp.domain.User;
import com.celisapp.dto.AuthorDTO;
import com.celisapp.repository.PostRepository;
import com.celisapp.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria , alex , bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"),"Partiu viagem","Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("21/03/2018"),"Bom dia","Acordei Feliz Hoje", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1 , post2));
		maria.getPost().addAll(Arrays.asList(post1, post2 ));
		userRepository.save(maria);
		
	}
}
