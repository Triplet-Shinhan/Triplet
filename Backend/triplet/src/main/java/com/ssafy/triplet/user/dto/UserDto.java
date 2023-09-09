package com.ssafy.triplet.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@JsonProperty("name")
	private String name;
	@JsonProperty("birth")
	private String birth;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("phoneNum")
	private String phoneNum;
	@JsonProperty("accountNum")
	private String accountNum;

	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setBirth(user.getBirth());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setPhoneNum(user.getPhoneNum());
		userDto.setAccountNum(user.getAccountNum());
		return userDto;
	}
}
