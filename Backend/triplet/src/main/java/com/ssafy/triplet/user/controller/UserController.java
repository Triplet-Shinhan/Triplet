package com.ssafy.triplet.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.triplet.user.dto.LoginDto;
import com.ssafy.triplet.user.dto.UserDto;
import com.ssafy.triplet.user.service.UserService;
import com.ssafy.triplet.user.util.UserUtility;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// @RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	private final UserUtility userUtility;

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
	public String login(@Validated @ModelAttribute LoginDto loginDto, BindingResult bindingResult,
		HttpServletResponse response) {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		if (userService.login(loginDto).orElse(null) != null) {
			Cookie idCookie = new Cookie("login_user", userUtility.getStringForCookie(loginDto));
			response.setContentType("text/html; charset=UTF-8");
			response.addCookie(idCookie);
//			return "api/trips"; //프로젝트 조회 페이지로 이동
			return "main";
		}

		bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
		return "login";// 로그인 페이지 유지
	}

	@PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("login_user", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "index";
//		return "redirect:/";
	}
}
