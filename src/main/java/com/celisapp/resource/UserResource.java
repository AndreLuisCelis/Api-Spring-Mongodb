package com.celisapp.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celisapp.domain.User;

@RestController
@RequestMapping(value ="/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll (){
		User maria = new User("1","maria brown", "maria@gmail.com");
		User mario = new User ("2", "mario brown", "mario@gameil.com");
		List <User> users = new ArrayList<User>();
		users.addAll(Arrays.asList(mario,maria));	
		return ResponseEntity.ok().body(users) ;	
	}

}
