package com.example.demo.service.StoreService;

import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.apiPayload.exception.handler.RegionNotFoundException;
import com.example.demo.converter.StoreConverter;
import com.example.demo.domain.Region;
import com.example.demo.domain.Store;
import com.example.demo.repository.RegionRepository.RegionRepository;
import com.example.demo.repository.StoreRepository.StoreRepository;
import com.example.demo.web.dto.AddStoreRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final RegionRepository regionRepo;
    private final StoreRepository storeRepo;

    @Override
    @Transactional
    public Long addStore(AddStoreRequest req) {
        Region region = regionRepo.findById(req.getRegionId())
                .orElseThrow(() -> new RegionNotFoundException(ErrorStatus.REGION_NOT_FOUND));

        Store store = StoreConverter.toEntity(req, region);
        storeRepo.save(store);
        return store.getId();
    }
}

