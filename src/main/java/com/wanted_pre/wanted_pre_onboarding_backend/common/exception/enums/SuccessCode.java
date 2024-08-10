package com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    CREATE_RECRUITMENT_SUCCESS(HttpStatus.OK, "채용 공고 등록 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
