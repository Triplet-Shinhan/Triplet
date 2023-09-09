package com.ssafy.triplet.exception;

public record ErrorResponse(
        int errorCode,
        String errorMsg
) {
}
