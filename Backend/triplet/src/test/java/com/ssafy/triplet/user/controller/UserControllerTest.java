package com.ssafy.triplet.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.repository.UserRepository;

@SpringBootTest
public class UserControllerTest {

	@Autowired
	private UserRepository userRepository;

	@Query(value = "ALTER TABLE users AUTO_INCREMENT = 1", nativeQuery = true)
	void resetAutoIncrement() {

	}

	@BeforeEach
	@Transactional
	public void reset() {
		userRepository.deleteAll();
		resetAutoIncrement();
	}

	@Test
	@Transactional
	@Rollback(false) // 테스트 종료 후 롤백하지 않도록 설정
	public void testSaveUser() {
		// Given
		User user = new User();
		user.setName("홍길동");
		user.setBirth("19970101");
		user.setEmail("hong@gil.dong");
		user.setPassword("gildong123");
		user.setPhoneNum("01012341234");
		user.setAccountNum("11223344556677");

		// When
		User savedUser = userRepository.save(user);

		// Then
		assertNotNull(savedUser.getUserId());
		assertEquals("홍길동", savedUser.getName());
		// 추가적인 검증 로직을 추가할 수 있습니다.
	}
}
