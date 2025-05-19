package com.example.demo.web.controller;

import com.example.demo.apiPayload.ApiResponse;

import com.example.demo.service.ReviewService.ReviewService;
import com.example.demo.validation.annotation.StoreExists;
import com.example.demo.web.dto.AddReviewRequest;
import com.example.demo.web.dto.ReviewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/stores/{storeId}/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponse> addReview(
            @PathVariable @StoreExists Long storeId,
            @Valid @RequestBody AddReviewRequest req
    ) {
        Long id = reviewService.addReview(storeId, req);
        return ApiResponse.onSuccess(new ReviewResponse(id));
    }
}