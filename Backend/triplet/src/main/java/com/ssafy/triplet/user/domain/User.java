package com.ssafy.triplet.user.domain;

import jakarta.persistence.Column;
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

	@Column(name = "name")
	private String name;
	@Column(name = "birth")
	private String birth;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "phoneNum")
	private String phoneNum;
	@Column(name = "accountNum")
	private String accountNum;
}
