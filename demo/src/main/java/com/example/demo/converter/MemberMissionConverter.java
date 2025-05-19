package com.example.demo.converter;

import com.example.demo.apiPayload.exception.GeneralException;
import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.domain.Member;
import com.example.demo.domain.Mission;
import com.example.demo.domain.enums.MissionStatus;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MissionRepository;
import com.example.demo.web.dto.ChallengeMissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.example.demo.apiPayload.exception.GeneralException;
import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.domain.Member;
import com.example.demo.domain.Mission;
import com.example.demo.domain.enums.MissionStatus;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MissionRepository;
import com.example.demo.web.dto.ChallengeMissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMissionConverter {

    private final MemberRepository   memberRepo;
    private final MissionRepository  missionRepo;

    public MemberMission toEntity(ChallengeMissionRequest req) {
        Member member = memberRepo.findById(req.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepo.findById(req.getMissionId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)  // “진행 중” 상태
                .build();
    }
}