package com.motolola.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.motolola.restfulwebservices.bean.User;
import com.motolola.restfulwebservices.bean.UserDaoService;
import com.motolola.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserResourceController {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		
		return service.findAll();
		
	}
	
	@GetMapping("/user/{id}")
	public User retriveUser(@PathVariable int id) {
		
		User user = service.findOne(id);
		
		if (user == null) 
			throw new UserNotFoundException("id -"+ id);
		
		return user;
	}

	@PostMapping("user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.
		fromCurrentRequest().
		path("/{id}").
		buildAndExpand(savedUser.getId()).
		toUri();
		
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("user/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user = service.remove(id);
		if (user == null) 
			throw new UserNotFoundException("id -"+ id);
	}

}
