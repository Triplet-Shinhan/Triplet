package com.ssafy.triplet.user.domain;

import com.ssafy.triplet.user.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	private Long userId;
	private String name;
	private String birth;
	private String email;
	private String password;
	private String phoneNum;
	private String accountNum;

	public static User toUserEntity(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setBirth(userDto.getBirth());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhoneNum(userDto.getPhoneNum());
		user.setAccountNum(userDto.getAccountNum());
		return user;
	}

	public static User toUpdateUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setBirth(userDto.getBirth());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhoneNum(userDto.getPhoneNum());
		user.setAccountNum(userDto.getAccountNum());
		return user;
	}
}
