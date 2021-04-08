package com.cg.css.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.css.exceptionhandling.InvalidUsernameOrPasswordException;
import com.cg.css.model.User;
import com.cg.css.repository.UserRepository;

@Transactional
@Service
public class UserService {

	@Autowired
	UserRepository userServiceRepo;

	public User registerUser(User user) {
		return userServiceRepo.save(user);
	}

	public List<User> viewAllUser() {
		return userServiceRepo.findAll();
	}

//	public String login(String username, String password) throws EntityNotFoundException, NullPointerException {
//		User user = userServiceRepo.findByUserName(username);
//		String login = "";
//		if (user == null)
//			throw new NullPointerException("incorrect username!");
//		if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
//			if (user.getRole().equals("customer")) {
//				login = "Logged in sucessfully as Customer!";
//				return login;
//			} else if (user.getRole().equals("admin"))
//				login = "Logged in sucessfully as Admin!";
//			return login;
//		}
//		throw new EntityNotFoundException("incorrect password!");
//	}
	
	
	
	public User login(String username, String password) throws EntityNotFoundException, NullPointerException, InvalidUsernameOrPasswordException {
		return userServiceRepo.findByUserName(username);
//		User userExists= userServiceRepo.findByUserNameAndPassword(username,password);
//		if (userExists == null) {
//			throw new InvalidUsernameOrPasswordException("Invalid Username Or Password! Try Again!");
//		}
//		else {
//			return userExists;
//		}
	}
	
	
	
	
	public User viewUser(int userId) throws EntityNotFoundException {
		return userServiceRepo.getOne(userId);
	}

	public User findByUsername(String username) {
		return userServiceRepo.findByUserName(username);
	}
}
