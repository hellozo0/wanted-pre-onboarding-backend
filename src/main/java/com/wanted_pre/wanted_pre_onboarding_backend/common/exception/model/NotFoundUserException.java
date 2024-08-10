package com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model;


import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundUserException extends WantedException {

    public NotFoundUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
