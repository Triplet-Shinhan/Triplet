package com.ssafy.triplet.user.repository;

import java.util.List;
import java.util.Optional;

import com.ssafy.triplet.user.domain.User;

public interface UserRepository {
	Optional<User> save(User user);
	List<User> findAll();
	Optional<User> findByUserId(Long userId);
	void update(User user);
}
