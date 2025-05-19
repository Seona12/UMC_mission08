package com.example.demo.service.MemberMissionService;

import com.example.demo.web.dto.ChallengeMissionRequest;

public interface MemberMissionService {
    Long challenge(ChallengeMissionRequest req);
}
