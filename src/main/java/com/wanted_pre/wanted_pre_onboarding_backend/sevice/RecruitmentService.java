package com.wanted_pre.wanted_pre_onboarding_backend.sevice;

import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.NotFoundException;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.dto.recruitment.RecruitmentCreateRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.dto.recruitment.RecruitmentUpdateRequest;
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
    public void postRecruitment(final RecruitmentCreateRequest request) {
        //1. 회사 존재 여부 찾기
        Enterprise enterprise = enterpriseRepository.findById(request.enterpriseId()).orElseThrow(() -> new NotFoundException(ErrorCode.ENTERPRISE_NOT_FOUND_EXCEPTION));

        //2. 등록하기
        Recruitment recruitment = new Recruitment(request.reward(), request.position(), request.detail(), request.skill(), enterprise);
        recruitmentRepository.save(recruitment);
    }

    @Transactional
    public void putRecruitment(final Long recruitmentId, final RecruitmentUpdateRequest request) {
        //1. 채용 공고 존재 여부 찾기
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(() -> new NotFoundException(ErrorCode.RECRUITMENT_NOT_FOUND_EXCEPTION));

        //2. 내용 업데이트
        recruitment.update(request.reward(), request.position(), request.detail(), request.skill());
        recruitmentRepository.save(recruitment);
    }

    @Transactional
    public void deleteRecruitment(final Long recruitmentId){
        //1. 채용 공고 존재 여부 찾기
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(() -> new NotFoundException(ErrorCode.RECRUITMENT_NOT_FOUND_EXCEPTION));

        //2. 삭제
        recruitmentRepository.delete(recruitment);
    }
}
