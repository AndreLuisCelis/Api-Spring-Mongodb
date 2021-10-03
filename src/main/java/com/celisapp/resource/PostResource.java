package com.celisapp.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.celisapp.domain.Post;
import com.celisapp.domain.User;
import com.celisapp.dto.UserDTO;
import com.celisapp.service.PostService;


@RestController
@RequestMapping(value ="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	

	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById (@PathVariable String id){
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post) ;	
	}		

}
