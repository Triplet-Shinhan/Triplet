package com.ssafy.triplet.user.util;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.daily.repository.DailyRepository;
import com.ssafy.triplet.payment.domain.Payment;
import com.ssafy.triplet.payment.repository.PaymentRepository;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.repository.TripRepository;
import jakarta.servlet.http.HttpServletRequest;
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
    private final TripRepository tripRepository;
    private final DailyRepository dailyRepository;
    private final PaymentRepository paymentRepository;

    private final UserUtility userUtility;

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

    public void checkTripValid(Long tripId, HttpServletRequest request) { //tripId ~ userId 검증
        User loginUser = userUtility.getUserFromCookie(request);
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));
        Long userId = trip.getUser().getUserId();
        if (!loginUser.getUserId().equals(userId)) {
            throw new BaseException(ErrorCode.NOT_AUTHORIZED);
        }
    }

    public void checkDailyValid(Long dailyId, HttpServletRequest request) { //dailYId ~ userId 검증
        User loginUser = userUtility.getUserFromCookie(request);
        Daily daily = dailyRepository.findById(dailyId).orElseThrow(() -> new BaseException(ErrorCode.DAILY_ID_NOT_FOUND));
        Long userId = daily.getUser().getUserId();
        if (!loginUser.getUserId().equals(userId)) {
            throw new BaseException(ErrorCode.NOT_AUTHORIZED);
        }
    }

    public void checkPaymentValid(Long paymentId, HttpServletRequest request) { //dailYId ~ userId 검증
        User loginUser = userUtility.getUserFromCookie(request);
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new BaseException(ErrorCode.PAYMENT_ID_NOT_FOUND));
        Long userId = payment.getTrip().getUser().getUserId();
        if (!loginUser.getUserId().equals(userId)) {
            throw new BaseException(ErrorCode.NOT_AUTHORIZED);
        }
    }
}
