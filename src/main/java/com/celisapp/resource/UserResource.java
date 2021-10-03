package com.celisapp.resource;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celisapp.domain.User;
import com.celisapp.dto.UserDTO;
import com.celisapp.service.UserService;

@RestController
@RequestMapping(value ="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll (){
		List <User> users = service.findAll();
		List <UserDTO> usersDTO = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDTO) ;	
	}

}
