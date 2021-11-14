package com.celisapp.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.celisapp.service.UserService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value ="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@ApiOperation(value = "Lista todos os usuários")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll (){
		List <User> users = service.findAll();
		List <UserDTO> usersDTO = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDTO) ;	
	}
	
	@ApiOperation(value = "Lista o usuário pelo ID")
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById (@PathVariable String id){
		User user = service.findById(id);
		UserDTO userDTO = new UserDTO(user);
		return ResponseEntity.ok().body(userDTO) ;	
	}
	
	@ApiOperation(value = "Cria um novo usuário")
	@PostMapping
	public ResponseEntity<Void> insert (@RequestBody UserDTO userDto){
		User user = service.fromDTO(userDto);
		 user = service.insert(user);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Deleta o usuário através do ID")
	@RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
	@ApiOperation(value = "Edita um usuário pelo ID")
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> update(@PathVariable String id , @RequestBody UserDTO userDto){
		User user = service.fromDTO(userDto);
		user.setId(id);
		user = service.update(user);
		UserDTO userDTO = new UserDTO(user);
		return ResponseEntity.ok().body(userDTO);	
	}
	
	@ApiOperation(value = "Lista os posts de um usuário com base no ID do usuário")
	@RequestMapping(value ="/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(user.getPost()) ;	
	}

}
