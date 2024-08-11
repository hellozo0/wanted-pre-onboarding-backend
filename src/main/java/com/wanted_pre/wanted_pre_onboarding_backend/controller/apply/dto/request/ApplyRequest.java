package com.wanted_pre.wanted_pre_onboarding_backend.controller.apply.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ApplyRequest(
        @Schema(description = "채용공고 아이디", example ="1")
        Long recruitmentId,
        @Schema(description = "유저 아이디", example ="1")
        Long userId
) {
}
