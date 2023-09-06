package com.ssafy.triplet.user.util;

import org.springframework.stereotype.Component;

import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.dto.LoginDto;
import com.ssafy.triplet.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUtility {
	private final UserRepository userRepository;

	//이름 | 아이디 형태로 문자열 반환
	public String getStringForCookie(LoginDto loginDto) {
		User user = userRepository.findByEmail(loginDto.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("User not found"));
		return "name:" + user.getName() + "|email:" + user.getEmail();
	}

	//1원호출 api 활용하여 검증하는 메서드

}
