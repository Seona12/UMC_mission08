package com.example.demo.service.MemberMissionService;

import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.validation.annotation.UserExists;
import com.example.demo.web.dto.ChallengeMissionRequest;
import org.springframework.data.domain.Page;

public interface MemberMissionService {
    Long challenge(ChallengeMissionRequest req);
    Page<MemberMission> getInProgressMissionsPage(
            @UserExists Long memberId,
            int page,
            int size
    );
}
