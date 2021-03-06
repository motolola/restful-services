package com.motolola.restfulwebservices.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private int usersCount = 3;

	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "John", new Date()));
		users.add(new User(3, "Toby", new Date()));
	};
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User remove(int id) {
		//User deletedUser = null;
		for (User user: users) {
			if (user.getId() == id) {
				User deletedUser = user; 
				users.remove(user);
				return deletedUser;
			}
		}
		return null;
	}
	

}
