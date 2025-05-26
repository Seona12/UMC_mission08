package com.example.demo.service.ReviewService;

import com.example.demo.domain.Review;
import com.example.demo.web.dto.AddReviewRequest;
import org.springframework.data.domain.Page;

public interface ReviewService {
    Long addReview(Long storeId, AddReviewRequest req);
    public Page<Review> getReviewsByUser(Long memberId, int page, int size);
}
