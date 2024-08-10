package com.wanted_pre.wanted_pre_onboarding_backend.controller.dto.recruitment;

import io.swagger.v3.oas.annotations.media.Schema;

public record RecruitmentCreateRequest(

        @Schema(description = "회사 아이디", example ="1")
        Long enterpriseId,
        @Schema(description = "채용 포지션", example ="백엔드 주니어 개발자")
        String position,
        @Schema(description = "채용 보상금", example ="1000000")
        int reward,
        @Schema(description = "채용 내용", example ="원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
        String detail,
        @Schema(description = "사용 기술", example ="Python")
        String skill
) {
}
