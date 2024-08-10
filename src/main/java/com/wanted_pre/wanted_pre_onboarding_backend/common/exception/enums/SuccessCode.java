package com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {



    FIND_DESIGNER_INFO_SUCCESS(HttpStatus.OK, "디자이너 정보 조회 성공"),
    DESIGNER_UPDATE_SUCCESS(HttpStatus.OK,"디자이너 정보 수정 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
