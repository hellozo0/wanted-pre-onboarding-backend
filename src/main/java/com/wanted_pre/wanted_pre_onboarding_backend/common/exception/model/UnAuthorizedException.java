package com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model;


import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;

public class UnAuthorizedException extends WantedException {
    public UnAuthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
