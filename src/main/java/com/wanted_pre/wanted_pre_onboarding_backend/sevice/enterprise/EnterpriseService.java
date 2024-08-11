package com.wanted_pre.wanted_pre_onboarding_backend.sevice.enterprise;

import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.NotFoundException;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.enterprise.Enterprise;
import com.wanted_pre.wanted_pre_onboarding_backend.repository.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    public Enterprise getEnterprise(final Long enterpriseId) {
        return  enterpriseRepository.findById(enterpriseId).orElseThrow(() -> new NotFoundException(ErrorCode.ENTERPRISE_NOT_FOUND_EXCEPTION));
    }
}
