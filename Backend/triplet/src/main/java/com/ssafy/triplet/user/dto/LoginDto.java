package com.ssafy.triplet.user.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginDto {
	private String email;
	private String password;
}
