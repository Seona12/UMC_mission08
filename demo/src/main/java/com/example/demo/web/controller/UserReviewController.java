package com.example.demo.web.controller;

import com.example.demo.converter.ReviewConverter;
import com.example.demo.service.ReviewService.ReviewService;
import com.example.demo.web.dto.StoreResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/reviews")
@RequiredArgsConstructor
public class UserReviewController {
    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "유저별 리뷰 페이징 조회")
    public ResponseEntity<StoreResponseDTO.ReviewPreViewListDTO> getUserReviews(
            @PathVariable Long userId,
            @RequestParam(name = "page", defaultValue = "1")
            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1") int page
    ) {
        int pageIndex = Math.max(page - 1, 0);
        var pageData = reviewService.getReviewsByUser(userId, pageIndex, 10);
        return ResponseEntity.ok(ReviewConverter.toListDto(pageData));
    }
}
