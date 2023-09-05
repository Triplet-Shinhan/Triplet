package com.ssafy.triplet.user.dto;

import com.ssafy.triplet.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
	private Long userId;
	private String name;
	private String birth;
	private String email;
	private String password;
	private String phoneNum;
	private String accountNum;

	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getName());
		userDto.setBirth(user.getBirth());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setPhoneNum(user.getPhoneNum());
		userDto.setAccountNum(user.getAccountNum());
		return userDto;
	}
}
