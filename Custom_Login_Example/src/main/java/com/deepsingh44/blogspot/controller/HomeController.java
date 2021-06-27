package com.deepsingh44.blogspot.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepsingh44.blogspot.model.CustomUserDetail;
import com.deepsingh44.blogspot.model.User;
import com.deepsingh44.blogspot.repository.UserRepository;

@RestController
public class HomeController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@GetMapping("/home")
	public String homePage(Principal principal) {
		User user = userRepository.findByUsername(principal.getName());
		return "Welcome to Home Page " + user.getName();
	}

}
