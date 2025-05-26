package com.example.demo.repository;

import com.example.demo.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    // storeId 로 모든 미션 조회
    List<Mission> findAllByStoreId(Long storeId);
}