package com.wanted_pre.wanted_pre_onboarding_backend.controller;

import com.wanted_pre.wanted_pre_onboarding_backend.common.dto.ErrorResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.common.dto.SuccessNonDataResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.SuccessCode;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.dto.recruitment.RecruitmentCreateRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.dto.recruitment.RecruitmentUpdateRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.sevice.RecruitmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Recruitment Controller", description = "채용공고 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @Operation(summary = "채용공고 등록", description = "채용 공고 등록 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채용공고 등록 성공", content = @Content(schema = @Schema(implementation = SuccessNonDataResponse.class))),
            @ApiResponse(responseCode = "404", description = "해당 회사를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public SuccessNonDataResponse createRecruitment(@Valid @RequestBody RecruitmentCreateRequest recruitmentCreateRequest) {
        recruitmentService.postRecruitment(recruitmentCreateRequest);
        return SuccessNonDataResponse.success(SuccessCode.CREATE_RECRUITMENT_SUCCESS);
    }

    @Operation(summary = "채용공고 수정", description = "채용 공고 수정 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채용공고 수정 성공", content = @Content(schema = @Schema(implementation = SuccessNonDataResponse.class))),
            @ApiResponse(responseCode = "404", description = "해당 채용공고를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping("/{recruitmentId}")
    public SuccessNonDataResponse updateRecruitment(@PathVariable(value = "recruitmentId") Long recruitmentId, @Valid @RequestBody RecruitmentUpdateRequest recruitmentUpdateRequest) {
        recruitmentService.putRecruitment(recruitmentId, recruitmentUpdateRequest);
        return SuccessNonDataResponse.success(SuccessCode.UPDATE_RECRUITMENT_SUCCESS);
    }

    @Operation(summary = "채용공고 삭제", description = "채용 공고 삭제 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채용공고 삭제 성공", content = @Content(schema = @Schema(implementation = SuccessNonDataResponse.class))),
            @ApiResponse(responseCode = "404", description = "해당 채용공고를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @DeleteMapping("/{recruitmentId}")
    public SuccessNonDataResponse removeRecruitment(@PathVariable(value = "recruitmentId") Long recruitmentId) {
        recruitmentService.deleteRecruitment(recruitmentId);
        return SuccessNonDataResponse.success(SuccessCode.DELETE_RECRUITMENT_SUCCESS);
    }

}
