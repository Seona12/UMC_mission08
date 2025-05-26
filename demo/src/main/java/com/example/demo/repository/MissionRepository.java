package com.example.demo.repository;

import com.example.demo.domain.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    // Pageable 추가
    Page<Mission> findAllByStoreId(Long storeId, Pageable pageable);
}