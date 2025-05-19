package com.example.demo.repository;

import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.domain.enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 이미 CHALLENGING(또는 CHALLENGING 이름으로 정의하셨다면 그 값) 상태인지 체크
    boolean existsByMember_IdAndMission_IdAndStatus(
            Long memberId, Long missionId, MissionStatus status
    );
}
