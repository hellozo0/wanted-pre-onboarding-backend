package com.wanted_pre.wanted_pre_onboarding_backend.sevice.user;

import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.NotFoundException;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.user.User;
import com.wanted_pre.wanted_pre_onboarding_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(final Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION));
    }
}
