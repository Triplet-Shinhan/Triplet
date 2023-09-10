package com.ssafy.triplet.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDto userDto) {
		logger.debug("signup request success");
		System.out.println(userDto);
		userService.signup(userDto);

		logger.debug("signup success");
		return ResponseEntity.ok().build();//헤더에만 성공 코드
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
		HttpServletResponse response) {
		logger.debug("login request success");
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
		sessionCookie.setSecure(true); // HTTPS를 사용해야만 합니다.
		sessionCookie.setHttpOnly(true); // JavaScript에서의 접근 방지
		response.addCookie(sessionCookie);

		// SameSite 속성을 raw 헤더에 추가
		String cookieHeader = String.format("%s; %s", sessionCookie.toString(), "SameSite=Lax");
		response.setHeader("Set-Cookie", cookieHeader);

		logger.debug("login success");

		return ResponseEntity.ok().build();
	}

	@PostMapping("/logout")
	public ResponseEntity<ApiResponse> logout(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("logout request success");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// 클라이언트 쿠키를 삭제하여 로그인 상태 해제
		Cookie sessionCookie = new Cookie("JSESSIONID", null);
		sessionCookie.setMaxAge(0);
		sessionCookie.setPath("/");
		response.addCookie(sessionCookie);
		logger.debug("logout request success");

		return ResponseEntity.ok().build();
	}
}
