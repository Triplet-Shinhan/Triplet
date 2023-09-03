package com.ssafy.triplet.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
	@Id
	private Long userId;
	private String name;
	private String birth;
	private String email;
	private String password;
	private String phoneNum;
	private String accountNum;
}
