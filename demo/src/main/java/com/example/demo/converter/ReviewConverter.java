package com.example.demo.converter;

import com.example.demo.domain.*;
import com.example.demo.apiPayload.exception.GeneralException;
import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.StoreRepository.StoreRepository;
import com.example.demo.web.dto.AddReviewRequest;
import com.example.demo.web.dto.ReviewResponse;
import com.example.demo.web.dto.StoreResponseDTO;
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


    public static StoreResponseDTO.ReviewPreViewDTO toDto(Review r) {
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                // Review 에는 ownerNickname 이 없으므로, 연관된 Member 에서 가져옵니다.
                .ownerNickname(r.getMember().getName())
                .score(r.getScore())
                .body(r.getBody())
                // BaseEntity 에 생성일(createdAt 또는 createdDate)이 LocalDateTime 으로 있다면 toLocalDate()
                // 만약 이미 LocalDate 라면 바로 r.getCreatedAt() 으로 바꾸세요.
                .createdAt(r.getCreatedAt().toLocalDate())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO toListDto(Page<Review> page) {
        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(page.getContent().stream()
                        .map(ReviewConverter::toDto)
                        .collect(Collectors.toList()))
                .listSize(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

}