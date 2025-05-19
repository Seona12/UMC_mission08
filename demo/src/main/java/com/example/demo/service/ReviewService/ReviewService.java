package com.example.demo.service.ReviewService;

import com.example.demo.web.dto.AddReviewRequest;

public interface ReviewService {
    Long addReview(Long storeId, AddReviewRequest req);
}
