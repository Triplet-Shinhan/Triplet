package com.ssafy.triplet.user.util;

import org.springframework.stereotype.Component;

import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.dto.LoginDto;
import com.ssafy.triplet.user.dto.UserDto;
import com.ssafy.triplet.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserValidation {
	private final UserRepository userRepository;

	public void checkSignupValid(UserDto userDto) {
		/* 입력 검증 */
		if (isInputInvalid("name", userDto.getName())) {
			throw new BaseException(ErrorCode.NAME_INPUT_ERROR);
		} else if (isInputInvalid("birth", userDto.getBirth())) {
			throw new BaseException(ErrorCode.BIRTH_INPUT_ERROR);
		} else if (isInputInvalid("email", userDto.getEmail())) {
			throw new BaseException(ErrorCode.EMAIL_INPUT_ERROR);
		} else if (isInputInvalid("password", userDto.getPassword())) {
			throw new BaseException(ErrorCode.PASSWORD_INPUT_ERROR);
		} else if (isInputInvalid("phoneNum", userDto.getPhoneNum())) {
			throw new BaseException(ErrorCode.PHONE_INPUT_ERROR);
		} else if (isInputInvalid("accountNum", userDto.getAccountNum())) {
			throw new BaseException(ErrorCode.ACCOUNT_INPUT_ERROR);
		}

		/* 데이터 검증 */
		if (userRepository.findByEmail(userDto.getEmail()).orElse(null) != null) {
			throw new BaseException(ErrorCode.EMAIL_DUPLICATED_ERROR);//이메일 중복
		} else if (userRepository.findByPhoneNum(userDto.getPhoneNum()).orElse(null) != null) {
			throw new BaseException(ErrorCode.PHONE_DUPLICATED_ERROR);//전화번호 중복
		} else if (userRepository.findByAccountNum(userDto.getAccountNum()).orElse(null) != null) {
			throw new BaseException(ErrorCode.ACCOUNT_DUPLICATED_ERROR);//계좌번호 중복
		}
	}

	public void checkLoginValid(LoginDto loginDto) {
		/* 입력 검증 */
		if (isInputInvalid("email", loginDto.getEmail())) {
			throw new BaseException(ErrorCode.EMAIL_INPUT_ERROR);
		} else if (isInputInvalid("password", loginDto.getPassword())) {
			throw new BaseException(ErrorCode.PASSWORD_INPUT_ERROR);
		}

		/* 데이터 검증 */
		User signUpUser = userRepository.findByEmail(loginDto.getEmail()).orElse(null);
		if (signUpUser == null) {//가입여부 검증
			throw new BaseException(ErrorCode.EMAIL_NOT_FOUND);//미가입 이메일
		} else if (!signUpUser.getPassword().equals(loginDto.getPassword())) {//비밀번호 검증
			throw new BaseException(ErrorCode.PASSWORD_INCORRECT);//비밀번호 오류
		}
	}

	private boolean isInputInvalid(String field, String target) {
		return target == null || target.isEmpty() || target.contains(" ") || !isRegexMatch(field, target);
	}

	private boolean isRegexMatch(String field, String target) {
		switch (field) {
			case "name" -> {
				return target.matches("^[가-힣]{2,6}$");
			}
			case "birth" -> {
				return target.matches("^(19|20)\\d\\d(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$");
			}
			case "email" -> {
				return target.matches(
					"^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
			}
			case "password" -> {
				return target.matches("(?=.*[A-Za-z])(?=.*[!@#$%^*+=-]).{8,15}$");
			}
			case "phoneNum" -> {
				return target.matches("[0-9]{3}[0-9]{4}[0-9]{4}$");
			}
			case "accountNum" -> {
				return target.matches("^[0-9]+$");
			}
			default -> {
				return false;
			}
		}
	}
}
