package com.wanted_pre.wanted_pre_onboarding_backend.sevice;

import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.NotFoundException;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.dto.recruitment.RecruitmentCreateRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.enterprise.Enterprise;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.recruitment.Recruitment;
import com.wanted_pre.wanted_pre_onboarding_backend.repository.EnterpriseRepository;
import com.wanted_pre.wanted_pre_onboarding_backend.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final EnterpriseRepository enterpriseRepository;
    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public void postRecruitment(final RecruitmentCreateRequest recruitmentCreateRequest) {
        //1. 회사 존재 여부 찾기
        Enterprise enterprise = enterpriseRepository.findById(recruitmentCreateRequest.enterpriseId()).orElseThrow(() -> new NotFoundException(ErrorCode.ENTERPRISE_NOT_FOUND_EXCEPTION));

        //2. 등록하기
        Recruitment recruitment = new Recruitment(recruitmentCreateRequest.reward(), recruitmentCreateRequest.position(), recruitmentCreateRequest.detail(), recruitmentCreateRequest.skill(), enterprise);
        recruitmentRepository.save(recruitment);
    }
}
