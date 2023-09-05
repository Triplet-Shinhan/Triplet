package com.ssafy.triplet.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.repository.UserRepository;

// @RestController
@Controller
@RequestMapping("/users")
public class UserController {
	// private final UserService userService;
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.ok("User saved successfully");
	}
}
