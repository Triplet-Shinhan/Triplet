package com.ssafy.triplet.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.dto.ApiResponse;
import com.ssafy.triplet.user.dto.LoginDto;
import com.ssafy.triplet.user.dto.UserDto;
import com.ssafy.triplet.user.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDto userDto) {
		userService.signup(userDto);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@Validated @ModelAttribute LoginDto loginDto, HttpSession session,
		BindingResult bindingResult,
		HttpServletResponse response) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}

		User loginUser = userService.login(loginDto).orElse(null);
		if (loginUser == null) {
			throw new BaseException(ErrorCode.LOGIN_FAILED);
		}

		session.setAttribute("email", loginUser.getEmail());
		Cookie idCookie = new Cookie("login_user", loginDto.getEmail());// 쿠키 저장값 수정 필요
		idCookie.setMaxAge(86400);//24시간
		response.addCookie(idCookie);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK));
	}

	@PostMapping("/logout")
	public ResponseEntity<ApiResponse> logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		Cookie cookie = new Cookie("login_user", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK));
	}
}
