package com.wanted_pre.wanted_pre_onboarding_backend.common.dto;

import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessNonDataResponse {
    private final int code;
    private final String message;

    public static SuccessNonDataResponse success(SuccessCode successCode) {
        return new SuccessNonDataResponse(successCode.getHttpStatus().value(), successCode.getMessage());
    }
}