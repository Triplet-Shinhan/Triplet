package com.ssafy.triplet.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.triplet.user.dto.UserDto;
import com.ssafy.triplet.user.service.UserService;

import lombok.RequiredArgsConstructor;

// @RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup")
	public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) {
		userService.signup(userDto);
		return ResponseEntity.ok("User saved successfully");
	}
}
