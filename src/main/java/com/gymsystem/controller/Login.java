package com.gymsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gymsystem.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/login")
public class Login {
	private UserServiceImpl userService;
	
	public Login(UserServiceImpl userService) {
        this.userService = userService;
    }
	
	@GetMapping("/login")
	public String handleRequest() {
		return userService.handleRequest();
	}
}
