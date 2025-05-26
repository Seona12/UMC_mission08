package com.example.demo.service;

import com.example.demo.domain.Mission;
import com.example.demo.repository.MissionRepository;
import com.example.demo.validation.annotation.StoreExists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;

    public List<Mission> getMissionsByStore(@StoreExists Long storeId) {
        return missionRepository.findAllByStoreId(storeId);
    }
}
