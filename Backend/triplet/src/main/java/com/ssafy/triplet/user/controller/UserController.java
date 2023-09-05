package com.ssafy.triplet.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.triplet.user.dto.UserDto;
import com.ssafy.triplet.user.service.UserService;

import jakarta.servlet.http.HttpSession;
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
	public ResponseEntity<String> saveUser(UserDto userDto) {
		userService.signup(userDto);
		return ResponseEntity.ok("User saved successfully");
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(UserDto userDto, HttpSession session) {
		return userService.login(userDto)
			.map(loginResult -> {
				session.setAttribute("email", loginResult.getEmail());
				return "api/trips"; //프로젝트 조회 페이지로 이동
			})
			.orElse("login");// 그대로 로그인 페이지
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
}
