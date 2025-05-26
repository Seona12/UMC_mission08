package com.example.demo.converter;

import com.example.demo.domain.*;
import com.example.demo.apiPayload.exception.GeneralException;
import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.StoreRepository.StoreRepository;
import com.example.demo.web.dto.AddReviewRequest;
import com.example.demo.web.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewConverter {
    private final StoreRepository storeRepo;
    private final MemberRepository memberRepo;

    public Review toEntity(Long storeId, AddReviewRequest req) {
        // 安全망: annotation 으로 store 존재 확인했어도, service/validator 통과 후 추가 검증 가능
        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Member member = memberRepo.findById(req.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        return Review.builder()
                .store(store)
                .member(member)
                .title(req.getTitle())
                .score(req.getScore())
                .build();
    }
    public ReviewResponse toDto(Review r) {
        return ReviewResponse.builder()
                .reviewId(r.getId())
                .title(r.getTitle())
                .body(r.getBody())
                .score(r.getScore())
                .storeName(r.getStore().getName())
                .memberName(r.getMember().getName())
                .createdAt(r.getCreatedAt())
                .build();
    }
    public List<ReviewResponse> toDtoList(Page<Review> page) {
        return page.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}