package com.wanted_pre.wanted_pre_onboarding_backend.sevice.apply;

import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.ConflictException;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.apply.dto.request.ApplyRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.apply.Apply;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.recruitment.Recruitment;
import com.wanted_pre.wanted_pre_onboarding_backend.domain.user.User;
import com.wanted_pre.wanted_pre_onboarding_backend.repository.ApplyRepository;
import com.wanted_pre.wanted_pre_onboarding_backend.sevice.recruitment.RecruitmentService;
import com.wanted_pre.wanted_pre_onboarding_backend.sevice.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final UserService userService;
    private final RecruitmentService recruitmentService;

    @Transactional
    public void applyRecruitment(final ApplyRequest applyRequest) {

        //1. 사용자가 해당 공고에 지원했는지 확인
        //존재 한다는 의미이다.
        if(applyRepository.existsByUserIdAndRecruitmentId(applyRequest.userId(), applyRequest.recruitmentId())){
            throw new ConflictException(ErrorCode.ALREADY_EXIST_EXCEPTION);
        }

        //2. 유저 찾기
        User user = userService.getUser(applyRequest.userId());

        //3. 채용공고 찾기
        Recruitment recruitment = recruitmentService.getRecruitment(applyRequest.recruitmentId());

        Apply apply = new Apply(user, recruitment);
        applyRepository.save(apply);

    }

}
