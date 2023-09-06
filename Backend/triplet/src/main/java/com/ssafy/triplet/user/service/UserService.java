package com.ssafy.triplet.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.dto.LoginDto;
import com.ssafy.triplet.user.dto.UserDto;
import com.ssafy.triplet.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public void signup(UserDto userDto) {
		User user = User.toUserEntity(userDto);
		userRepository.save(user);
	}

	public Optional<User> login(LoginDto loginDto) {
		return userRepository.findByEmail(loginDto.getEmail())
			.filter(user -> user.getPassword().equals(loginDto.getPassword()));
	}
}
