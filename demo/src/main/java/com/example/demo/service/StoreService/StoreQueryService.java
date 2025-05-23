package com.example.demo.service.StoreService;


import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import com.example.demo.web.dto.StoreResponseDTO;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);

    StoreResponseDTO.ReviewPreViewListDTO getReviewList(Long storeId, Integer page);
}