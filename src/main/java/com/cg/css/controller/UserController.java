package com.cg.css.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.css.exceptionhandling.PasswordAndConfirmPasswordDoNotMatchException;
import com.cg.css.exceptionhandling.UsernameAlreadyExistsException;
import com.cg.css.model.User;
import com.cg.css.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;

	@PostMapping(value = "/signIn")
	public User registerUser(@RequestBody @Valid User user, BindingResult br)
			throws UsernameAlreadyExistsException, Exception {
		String username = user.getUserName();
		User usernameExists = service.findByUsername(username);
		if (usernameExists != null) {
			throw new UsernameAlreadyExistsException("Username already exists!!");
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new PasswordAndConfirmPasswordDoNotMatchException("Your password and confirm password do not match!");
		}
		return service.registerUser(user);
	}

//	@GetMapping(value = "/login")
//	public String login(@RequestParam("username") String username, @RequestParam("password") String password)
//			throws Exception {
//		return service.login(username, password);
//	}
	
	@GetMapping(value = "/login")
	public User login(@RequestParam("username") String username, @RequestParam("password") String password)
			throws Exception {
		System.out.println(username+"Hello "+password);
		return service.login(username, password);
	}

	@GetMapping("/viewAllUsers")
	public ResponseEntity<List<User>> viewAllUser() {
		List<User> allUser = service.viewAllUser();
		return new ResponseEntity<List<User>>(allUser, HttpStatus.OK);
	}

	@GetMapping("/viewUser")
	public ResponseEntity<User> viewUser(@RequestParam("userId") int userId) {
		User user = service.viewUser(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
