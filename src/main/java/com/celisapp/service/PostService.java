package com.celisapp.service;

import java.util.Date;
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
	
	public List<Post> fullSearch(String text , Date minDate , Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
	
	public List<Post> getAllPosts(){
		return repo.findAll();
	}
	
	public void addPost(Post post) {
		repo.save(post);
	}
	

}
