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
import com.celisapp.dto.CommentDTO;
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
		
		CommentDTO c1 = new CommentDTO("Boa viagem Mano", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Proveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um Bom dia", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1 , post2));
		maria.getPost().addAll(Arrays.asList(post1, post2 ));
		userRepository.save(maria);
		
	}
}
