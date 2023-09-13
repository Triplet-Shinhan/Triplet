package com.ssafy.triplet.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.dto.LoginDto;
import com.ssafy.triplet.user.dto.UserDto;
import com.ssafy.triplet.user.repository.UserRepository;
import com.ssafy.triplet.user.util.UserValidation;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final UserValidation userValidation;

	public void signup(UserDto userDto) {
		//검증
		userValidation.checkSignupValid(userDto);

		//가입
		User user = new User().toUserEntity(userDto);
		userRepository.save(user);
	}

	public Optional<User> login(LoginDto loginDto) {
		//검증
		userValidation.checkLoginValid(loginDto);

		// 로그인
		return userRepository.findByEmail(loginDto.getEmail())
			.filter(user -> user.getPassword().equals(loginDto.getPassword()));
	}
}
