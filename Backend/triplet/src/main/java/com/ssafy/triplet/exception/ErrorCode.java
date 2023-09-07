package com.ssafy.triplet.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    //회원가입
    NAME_INPUT_ERROR(400, "올바르지 않은 이름입니다."),
    BIRTH_INPUT_ERROR(400, "올바르지 않은 생년월일입니다."),
    EMAIL_INPUT_ERROR(400, "올바르지 않은 이메일 형식입니다."),
    EMAIL_DUPLICATED_ERROR(409, "이미 가입된 이메일입니다."),
    PASSWORD_INPUT_ERROR(400, "올바르지 않은 비밀번호 형식입니다."),
    PHONE_INPUT_ERROR(400, "올바르지 않은 전화번호입니다."),
    ACCOUNT_INPUT_ERROR(400, "올바르지 않은 계좌번호입니다."),
    ACCOUNT_DUPLICATED_ERROR(409, "이미 가입된 계좌번호입니다."),
    //로그인
    EMAIL_NOT_FOUND(401, "존재하지 않는 아이디입니다."),
    PASSWORD_ERROR(401, "일치하지 않는 비밀번호 입니다."),
    LOGIN_FAILED(400, "로그인에 실패하였습니다."),
    //세션
    COOKIE_ERROR(401, "로그인하지 않은 상태입니다."),
    //프로젝트
    USER_ID_NOT_FOUND(401, "유저 아이디를 찾을 수 없습니다."),
    TRIP_ID_NOT_FOUND(401, "프로젝트 아이디를 찾을 수 없습니다."),
    DAILY_ID_NOT_FOUND(401, "일일 아이디를 찾을 수 없습니다."),
    //지출
    COST_INPUT_ERROR(400, "올바르지 않은 금액입니다."),
    PAYMENT_ID_NOT_FOUND(401, "결제 아이디를 찾을 수 없습니다."),

    //전역
    BAD_REQUEST(400, "잘못된 입력입니다."),
    NOT_AUTHORIZED(401, "권한이 없습니다."),
    FORBIDDEN_ERROR(403, "접근이 제한되어있습니다."),
    NOT_FOUND(404, "찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "메서드가 허용되지 않았습니다."),//메서드 매칭이 안될 경우
    NETWORK_FAIL_ERROR(503, "네트워크 오류가 발생했습니다."),
    RESPONSE_DELAY_ERROR(403, "응답이 지연되고 있습니다. 나중에 다시 시도해 주세요."),
    SERVER_ERROR(500, "예상치 못한 서버 에러가 발생했습니다.");

    private final int errorCode;
    private final String errorMsg;
}
