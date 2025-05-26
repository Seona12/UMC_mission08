package com.example.demo.service;

import com.example.demo.domain.Mission;
import com.example.demo.repository.MissionRepository;
import com.example.demo.validation.annotation.StoreExists;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;

    public Page<Mission> getMissionsByStore(Long storeId, int page, int size) {
        // storeExists 검증은 커스텀 애노테이션 또는 서비스 레벨에서 처리
        return missionRepository.findAllByStoreId(
                storeId,
                PageRequest.of(page, size, Sort.by("deadline").ascending())
        );
    }
}
