package com.celisapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.celisapp.domain.User;
import com.celisapp.dto.UserDTO;
import com.celisapp.repository.UserRepository;
import com.celisapp.service.exception.ObjectNotFoundException;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll (){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(()-> new ObjectNotFoundException("Usuário não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		 repo.deleteById(id);
	}
	
	public User update(User obj) {
		User user = findById(obj.getId());
		updateData(user , obj);
		return repo.save(user);
	}
	
	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}

}
