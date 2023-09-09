package com.ssafy.triplet.user.domain;

import com.ssafy.triplet.user.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

	@Column(nullable = false)
	@NotBlank(message = "공백이 포함되어 있습니다.")
	@Pattern(regexp = "^[가-힣]{2,4}$", message = "이름은 2~4글자의 한글로 입력해주세요.")
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "공백이 포함되어 있습니다.")
	@Pattern(regexp = "^(19|20)\\d\\d(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "올바른 생년월일 형식(8자리 년/월/일)을 입력하세요.")
	private String birth;

	@Column(nullable = false)
	@NotBlank(message = "공백이 포함되어 있습니다.")
	@Email(message = "이메일 형식에 맞게 입력해주세요.")
	private String email;

	@Column(nullable = false)
	@NotBlank(message = "공백이 포함되어 있습니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[!@#$%^*+=-]).{8,15}$", message = "비밀번호는 8~15자리이며, 특수문자를 하나 이상을 포함해야 합니다.")
	private String password;

	@Column(nullable = false)
	@NotBlank(message = "공백이 포함되어 있습니다.")
	@Pattern(regexp = "^[0-9]{3}[0-9]{4}[0-9]{4}$", message = "올바르지 않은 전화번호 형식입니다. 형식에 맞게 핸드폰 번호 11자리를 입력해주세요.")
	private String phoneNum;

	@Column(nullable = false)
	@NotBlank(message = "공백이 포함되어 있습니다.")
	@Pattern(regexp = "^[0-9]+$", message = "계좌번호에는 숫자만 입력할 수 있습니다.")
	private String accountNum;

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
