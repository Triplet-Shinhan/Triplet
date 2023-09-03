package com.ssafy.triplet.exception;

public record ErrorResponse(
        ErrorCode errorCode,
        String errorMsg
) {
}
