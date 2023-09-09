package com.ssafy.triplet.user.controller;

import org.springframework.http.ResponseEntity;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> saveUser(@Valid @RequestBody UserDto userDto) {
		userService.signup(userDto);
		return ResponseEntity.ok().build();//헤더에만 성공 코드
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request,
		HttpServletResponse response) {
		User loginUser = userService.login(loginDto).orElse(null);
		if (loginUser == null) {
			throw new BaseException(ErrorCode.LOGIN_FAILED);
		}

		// 세션을 설정하여 사용자 정보를 저장
		HttpSession session = request.getSession();
		session.setAttribute("user", loginUser);

		// 쿠키를 통해 세션 ID를 클라이언트에 저장
		Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
		sessionCookie.setMaxAge(86400); // 24시간
		sessionCookie.setPath("/");
		response.addCookie(sessionCookie);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/logout")
	public ResponseEntity<ApiResponse> logout(@Valid HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// 클라이언트 쿠키를 삭제하여 로그인 상태 해제
		Cookie sessionCookie = new Cookie("JSESSIONID", null);
		sessionCookie.setMaxAge(0);
		sessionCookie.setPath("/");
		response.addCookie(sessionCookie);

		return ResponseEntity.ok().build();
	}
}
