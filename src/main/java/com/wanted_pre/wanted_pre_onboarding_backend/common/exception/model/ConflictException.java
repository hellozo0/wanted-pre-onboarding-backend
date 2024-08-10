package com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model;


import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;

public class ConflictException extends WantedException {
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
