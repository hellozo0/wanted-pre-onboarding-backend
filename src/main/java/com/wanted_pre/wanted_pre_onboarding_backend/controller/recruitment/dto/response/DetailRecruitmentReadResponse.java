package com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DetailRecruitmentReadResponse(
        @Schema(description = "채용공고 아이디", example ="1")
        Long recruitmentId,
        @Schema(description = "회사명", example ="원티드")
        String enterpriseName,
        @Schema(description = "국가", example ="한국")
        String country,
        @Schema(description = "지역", example ="서울")
        String location,
        @Schema(description = "채용 포지션", example ="백엔드 주니어 개발자")
        String position,
        @Schema(description = "채용 보상금", example ="1000000")
        int reward,
        @Schema(description = "사용 기술", example ="Python")
        String skill,
        @Schema(description = "채용 내용", example ="원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
        String detail,
        @Schema(description = "회사가올린다른채용공고", example ="[1,2,3]")
        List<Long> anotherRecruitmentId
) {
}
