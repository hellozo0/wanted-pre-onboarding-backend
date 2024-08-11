package com.wanted_pre.wanted_pre_onboarding_backend.sevice.recruitment;

import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.NotFoundException;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment.dto.request.RecruitmentCreateRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment.dto.request.RecruitmentUpdateRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment.dto.response.DetailRecruitmentReadResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment.dto.response.RecruitmentReadResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.enterprise.Enterprise;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.recruitment.Recruitment;
import com.wanted_pre.wanted_pre_onboarding_backend.repository.EnterpriseRepository;
import com.wanted_pre.wanted_pre_onboarding_backend.repository.RecruitmentRepository;
import com.wanted_pre.wanted_pre_onboarding_backend.sevice.enterprise.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final EnterpriseService enterpriseService;

    @Transactional
    public void postRecruitment(final RecruitmentCreateRequest request) {
        //1. 회사 존재 여부 찾기
        Enterprise enterprise = enterpriseService.getEnterprise(request.enterpriseId());

        //2. 등록하기
        Recruitment recruitment = new Recruitment(request.reward(), request.position(), request.detail(), request.skill(), enterprise);
        recruitmentRepository.save(recruitment);
    }

    @Transactional
    public void putRecruitment(final Long recruitmentId, final RecruitmentUpdateRequest request) {
        //1. 채용 공고 존재 여부 찾기
        Recruitment recruitment = getRecruitment(recruitmentId);
        //2. 내용 업데이트
        recruitment.update(request.reward(), request.position(), request.detail(), request.skill());
        recruitmentRepository.save(recruitment);
    }

    @Transactional
    public void deleteRecruitment(final Long recruitmentId){
        //1. 채용 공고 존재 여부 찾기
        Recruitment recruitment = getRecruitment(recruitmentId);
        //2. 삭제
        recruitmentRepository.delete(recruitment);
    }

    public List<RecruitmentReadResponse> getRecruitment() {
        List<Recruitment> recruitmentList = recruitmentRepository.findAll();

        return getRecruitmentResponse(recruitmentList);
    }

    public List<RecruitmentReadResponse> getRecruitmentBySearch(String search){
        //1. 만약 빈 문자열일 때 null 에러 나는거 피하기 위해
        if(search == null) search = "";

        //2. 회사명으로 검색
        List<Recruitment> recruitmentListByEnterprise = recruitmentRepository.findByEnterpriseNameContainingIgnoreCase(search);

        //3. 기술명으로 검색
        List<Recruitment> recruitmentListBySkill = recruitmentRepository.findBySkillContainingIgnoreCase(search);

        //4. 합치기(중복제거)
        Set<Recruitment> recruitmentSet = new LinkedHashSet<>(recruitmentListByEnterprise);
        recruitmentSet.addAll(recruitmentListBySkill);

        List<Recruitment> recruitmentList = new ArrayList<>(recruitmentSet);

        //5. 만약 검색 결과가 없다면
        if(recruitmentList.size() == 0) return new ArrayList<>();

        return getRecruitmentResponse(recruitmentList);
    }

    public DetailRecruitmentReadResponse getDetailRecruitment(final Long recruitmentId){
        Recruitment recruitment = getRecruitment(recruitmentId);
        List<Long> anotherRecruitmentIdList = recruitmentRepository.findRecruitmentIdsByEnterpriseId(recruitment.getEnterprise().getId(), recruitmentId);

        DetailRecruitmentReadResponse detailRecruitmentReadResponse = new DetailRecruitmentReadResponse(
                recruitmentId,
                recruitment.getEnterprise().getName(),
                recruitment.getEnterprise().getCountry(),
                recruitment.getEnterprise().getLocation(),
                recruitment.getPosition(),
                recruitment.getReward(),
                recruitment.getSkill(),
                recruitment.getDetail(),
                anotherRecruitmentIdList
        );

        return detailRecruitmentReadResponse;
    }

    public Recruitment getRecruitment(final Long recruitmentId) {
        return recruitmentRepository.findById(recruitmentId).orElseThrow(() -> new NotFoundException(ErrorCode.RECRUITMENT_NOT_FOUND_EXCEPTION));
    }

    private List<RecruitmentReadResponse> getRecruitmentResponse(List<Recruitment> recruitmentList) {
        List<RecruitmentReadResponse> recruitmentReadResponseList = recruitmentList.stream().map( recruitment -> {
            RecruitmentReadResponse  recruitmentReadResponse = new RecruitmentReadResponse(
                    recruitment.getId(),
                    recruitment.getEnterprise().getName(),
                    recruitment.getEnterprise().getCountry(),
                    recruitment.getEnterprise().getLocation(),
                    recruitment.getPosition(),
                    recruitment.getReward(),
                    recruitment.getSkill()
            );
            return recruitmentReadResponse;
        }).collect(Collectors.toList());

        return  recruitmentReadResponseList;
    }
}
