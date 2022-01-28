package com.coderscampus.week13.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.week13.service.UserService;

@RestController
public class UserController {
	
	private UserService userService;
	
	public UserController (UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public String getUsers () {
		return userService.getMessage();
	}
}
