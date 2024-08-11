package com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment;

import com.wanted_pre.wanted_pre_onboarding_backend.common.dto.ErrorResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.common.dto.SuccessNonDataResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.common.dto.SuccessResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.SuccessCode;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment.dto.request.RecruitmentCreateRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment.dto.request.RecruitmentUpdateRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment.dto.response.DetailRecruitmentReadResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.recruitment.dto.response.RecruitmentReadResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.sevice.recruitment.RecruitmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Operation(summary = "채용공고 전체 조회", description = "채용 공고 전체 조회 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채용공고 전체조회 성공", content = @Content(schema = @Schema(implementation = SuccessNonDataResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping
    public SuccessResponse<List<RecruitmentReadResponse>> readRecruitment() {
        return SuccessResponse.success(SuccessCode.GET_RECRUITMENT_SUCCESS, recruitmentService.getRecruitment());
    }

    @Operation(summary = "채용공고 검색 조회", description = "채용 공고 검색 조회 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채용공고 검색조회 성공", content = @Content(schema = @Schema(implementation = SuccessNonDataResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/v2")
    public SuccessResponse<List<RecruitmentReadResponse>> searchRecruitment(@Parameter(name = "search", description = "회사명/기술명") @RequestParam(value = "search") String search) {
        return SuccessResponse.success(SuccessCode.GET_RECRUITMENT_SUCCESS, recruitmentService.getRecruitmentBySearch(search));
    }

    @Operation(summary = "채용공고 상세 조회", description = "채용공고 상세 조회 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채용공고 상세 조회 성공", content = @Content(schema = @Schema(implementation = SuccessNonDataResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/detail/{recruitmentId}")
    public SuccessResponse<DetailRecruitmentReadResponse> readDetailRecruitment(@PathVariable(value = "recruitmentId") Long recruitmentId) {
        return SuccessResponse.success(SuccessCode.GET_DETAIL_RECRUITMENT_SUCCESS, recruitmentService.getDetailRecruitment(recruitmentId));
    }

}
