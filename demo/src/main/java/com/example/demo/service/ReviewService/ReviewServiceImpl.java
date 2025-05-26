package com.example.demo.service.ReviewService;

import com.example.demo.converter.ReviewConverter;
import com.example.demo.domain.Review;
import com.example.demo.repository.ReviewRepository.ReviewRepository;
import com.example.demo.web.dto.AddReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepo;
    private final ReviewConverter reviewConverter;

    @Override
    @Transactional
    public Long addReview(Long storeId, AddReviewRequest req) {
        Review review = reviewConverter.toEntity(storeId, req);
        return reviewRepo.save(review).getId();
    }

    public Page<Review> getReviewsByUser(Long userId, int page, int size) {
        return reviewRepo.findAllByMemberId(
                userId,
                PageRequest.of(page, size, Sort.by("createdAt").descending())
        );
    }
}