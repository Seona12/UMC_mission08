package com.example.demo.validation.validator;

import com.example.demo.domain.enums.MissionStatus;
import com.example.demo.repository.MemberMissionRepository;
import com.example.demo.validation.annotation.MissionNotChallenged;
import com.example.demo.web.dto.ChallengeMissionRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionNotChallengedValidator
        implements ConstraintValidator<MissionNotChallenged, ChallengeMissionRequest> {

    private final MemberMissionRepository repo;

    @Override
    public boolean isValid(ChallengeMissionRequest req, ConstraintValidatorContext context) {
        // memberId, missionId null 은 @NotNull 이 잡아주므로 여기선 패스
        if (req.getMemberId() == null || req.getMissionId() == null) {
            return true;
        }
        // 이미 CHALLENGING 상태로 등록된 적이 있으면 false 리턴 → ConstraintViolation
        return !repo.existsByMember_IdAndMission_IdAndStatus(
                req.getMemberId(),
                req.getMissionId(),
                MissionStatus.CHALLENGING
        );
    }
}