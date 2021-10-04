package com.celisapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.celisapp.domain.Post;
import com.celisapp.repository.PostRepository;
import com.celisapp.service.exception.ObjectNotFoundException;


@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(()-> new ObjectNotFoundException("Usuário não encontrado"));
	}
	
	public List<Post> findByTitle(String title){
		return repo.searchTitle(title);
	}
	

}