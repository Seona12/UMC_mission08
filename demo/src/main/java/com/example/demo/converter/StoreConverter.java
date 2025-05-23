package com.example.demo.converter;

import com.example.demo.domain.Region;
import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import com.example.demo.web.dto.AddStoreRequest;
import com.example.demo.web.dto.StoreResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {
    public static Store toEntity(AddStoreRequest req, Region region) {
        return Store.builder()
                .name(req.getName())
                .address(req.getAddress())
                // score 초기값이 필요하다면 0.0f 같이 지정
                .region(region)
                .build();
    }

    // ... 기타 코드들 (8주차 미션에 있었던 리뷰 추가 등등)


    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
