package com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400
    INVALID_VALUE_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 타입 값을 입력했습니다."),
    INVALID_EMPTY_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "해당 값은 null 또 상수 값이 유효하지 않습니다."),
    VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청값이 유효하지 않습니다."),

    // 401

    //404
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),
    ENTERPRISE_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 회사는 존재하지 않습니다."),
    RECRUITMENT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 채용공고는 존재하지 않습니다."),
    NOT_FOUND_RESOURCE_EXCEPTION(HttpStatus.NOT_FOUND, "해당 자원을 찾을 수 없습니다."),

    // 405 METHOD_NOT_ALLOWED
    METHOD_NOT_ALLOWED_EXCEPTION(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 메소드 입니다."),

    // 409 Conflict
    ALREADY_EXIST_USER_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 유저입니다."),
    ALREADY_EXIST_EXCEPTION(HttpStatus.CONFLICT, "이미 지원되었습니다."),

    // 500
    INTERNAL_SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
