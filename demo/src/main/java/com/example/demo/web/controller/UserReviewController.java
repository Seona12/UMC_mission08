package com.example.demo.web.controller;

import com.example.demo.converter.ReviewConverter;
import com.example.demo.service.ReviewService.ReviewService;
import com.example.demo.validation.annotation.UserExists;
import com.example.demo.web.annotation.PageParam;
import com.example.demo.web.dto.StoreResponseDTO;
import com.example.demo.apiPayload.code.ErrorReasonDTO;
import com.example.demo.apiPayload.code.status.ErrorStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/reviews")
@RequiredArgsConstructor
public class UserReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @Operation(
            summary = "유저별 리뷰 페이징 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "조회 성공",
                            content = @Content(schema = @Schema(implementation = StoreResponseDTO.ReviewPreViewListDTO.class))),
                    @ApiResponse(responseCode = "400", description = "MEMBER4001: 사용자 정보가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorReasonDTO.class)))
            }
    )
    public ResponseEntity<StoreResponseDTO.ReviewPreViewListDTO> getUserReviews(
            @Parameter(description = "유저 ID", example = "1", required = true)
            @PathVariable @UserExists Long userId,

            @Parameter(
                    name = "page",
                    description = "페이지 번호 (1부터 시작)",
                    example = "1",
                    in = ParameterIn.QUERY,
                    required = true
            )
            @PageParam
            int page  // Resolver가 1→0 변환, 0 이하면 예외 처리
    ) {
        var pageData = reviewService.getReviewsByUser(userId, page, 10);
        return ResponseEntity.ok(ReviewConverter.toListDto(pageData));
    }
}
