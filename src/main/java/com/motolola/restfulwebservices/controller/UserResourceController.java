package com.motolola.restfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.motolola.restfulwebservices.bean.User;
import com.motolola.restfulwebservices.bean.UserDaoService;

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
		return service.findOne(id);
	}

}
