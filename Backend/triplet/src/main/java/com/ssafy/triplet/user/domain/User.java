package com.ssafy.triplet.user.domain;

import java.util.List;

import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.user.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	private Long userId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String birth;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String phoneNum;

	@Column(nullable = false)
	private String accountNum;

	@OneToMany(mappedBy = "user")
	@JoinColumn(name = "trip_id")
	private List<Trip> trips;

	public User toUserEntity(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setBirth(userDto.getBirth());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhoneNum(userDto.getPhoneNum());
		user.setAccountNum(userDto.getAccountNum());
		return user;
	}
}
