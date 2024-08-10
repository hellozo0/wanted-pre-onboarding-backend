package com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model;

import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;
import lombok.Getter;

@Getter
public class WantedException extends RuntimeException {
    private final ErrorCode errorCode;

    public WantedException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}