package com.wanted_pre.wanted_pre_onboarding_backend.controller.apply;


import com.wanted_pre.wanted_pre_onboarding_backend.common.dto.ErrorResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.common.dto.SuccessNonDataResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.SuccessCode;
import com.wanted_pre.wanted_pre_onboarding_backend.controller.apply.dto.request.ApplyRequest;
import com.wanted_pre.wanted_pre_onboarding_backend.sevice.apply.ApplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Apply Controller", description = "지원하기 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {

    private final ApplyService applyService;

    @Operation(summary = "지원하기", description = "지원하기 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "지원하기 성공", content = @Content(schema = @Schema(implementation = SuccessNonDataResponse.class))),
            @ApiResponse(responseCode = "404", description = "해당 채용공고를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "해당 유를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public SuccessNonDataResponse apply(@Valid @RequestBody ApplyRequest applyRequest) {
        applyService.applyRecruitment(applyRequest);
        return SuccessNonDataResponse.success(SuccessCode.APPLY_SUCCESS);
    }
}
