package com.ssafy.triplet.user.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.user.domain.User;

import jakarta.transaction.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	@Override
	public Optional<User> save(final User user) {
		String sql = "INSERT INTO users (name, birth, email, password, phoneNum, accountNum) " +
			"VALUES (:name, :birth, :email, :password, :phoneNum, :accountNum)";

		if (!isExistUserByUserId(user.getUserId())) {
			jdbcTemplate.update(sql, mapUserToSqlParam(user));
			return Optional.of(user);
		}
		throw new BaseException(ErrorCode.EMAIL_DUPLICATED_ERROR);
	}

	private boolean isExistUserByUserId(final Long userId) {
		try {
			return Boolean.TRUE.equals(jdbcTemplate.queryForObject(
				"SELECT EXISTS (SELECT user_id FROM users WHERE user_id = :userId)",
				Map.of("userId", userId), Boolean.class));
		} catch (final EmptyResultDataAccessException e) {
			throw new BaseException(ErrorCode.EMAIL_NOT_FOUND);
		}
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM users";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
	}

	@Override
	public Optional<User> findByUserId(Long userId) {

	}

	@Override
	public void update(User user) {
		String sql = "UPDATE users " +
			"SET name = :name, birth = :birth, password = :password, phoneNum = :phoneNum, accountNum = :accountNum " +
			"WHERE user_id = :userId";

		jdbcTemplate.update(sql, mapUserToSqlParam(user));
	}

	private MapSqlParameterSource mapUserToSqlParam(User user) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap
			.addValue("email", user.getEmail())
			.addValue("name", user.getName())
			.addValue("birth", user.getBirth())
			.addValue("password", user.getPassword())
			.addValue("phoneNum", user.getPhoneNum())
			.addValue("accountNum", user.getAccountNum());
		return paramMap;
	}
}
