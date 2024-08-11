package com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    CREATE_RECRUITMENT_SUCCESS(HttpStatus.OK, "채용 공고 등록 성공"),
    UPDATE_RECRUITMENT_SUCCESS(HttpStatus.OK, "채용 공고 수정 성공"),
    DELETE_RECRUITMENT_SUCCESS(HttpStatus.OK, "채용 공고 삭제 성공"),
    GET_RECRUITMENT_SUCCESS(HttpStatus.OK, "채용 공고 조회 성공"),
    GET_DETAIL_RECRUITMENT_SUCCESS(HttpStatus.OK, "채용 공고 상세 조회 성공"),
    APPLY_SUCCESS(HttpStatus.OK, "지원하기 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
