package com.example.demo.service.StoreService;

import com.example.demo.converter.StoreConverter;
import com.example.demo.domain.Review;
import com.example.demo.domain.Store;
import com.example.demo.repository.ReviewRepository.ReviewRepository;
import com.example.demo.repository.StoreRepository.StoreRepository;
import com.example.demo.web.dto.StoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }
    @Override
    public StoreResponseDTO.ReviewPreViewListDTO getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다: id = " + storeId));

        Page<Review> reviewPage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));

        return StoreConverter.reviewPreViewListDTO(reviewPage);
    }
}