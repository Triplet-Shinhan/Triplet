package com.ssafy.triplet.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDto {
	@NotBlank(message = "공백이 포함되어 있습니다.")
	@Email(message = "이메일 형식에 맞게 입력해주세요.")
	private String email;

	@NotBlank(message = "공백이 포함되어 있습니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[!@#$%^*+=-]).{8,15}$", message = "비밀번호는 8~15자리이며, 특수문자를 하나 이상을 포함해야 합니다.")
	private String password;
}
