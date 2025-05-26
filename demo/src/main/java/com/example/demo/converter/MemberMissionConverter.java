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
import com.example.demo.web.dto.InProgressMissionDTO;
import com.example.demo.web.dto.InProgressMissionPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

import java.util.stream.Collectors;

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
    public static InProgressMissionDTO toDto(MemberMission mm) {
        return InProgressMissionDTO.builder()
                .missionId(mm.getMission().getId())
                .missionSpec(mm.getMission().getMissionSpec())
                .reward(mm.getMission().getReward())
                .deadline(mm.getMission().getDeadline())
                .createdAt(mm.getCreatedAt().toLocalDate())
                .build();
    }

    public static InProgressMissionPageDTO toPageDto(Page<MemberMission> page) {
        return InProgressMissionPageDTO.builder()
                .missions(page.getContent().stream()
                        .map(MemberMissionConverter::toDto)
                        .collect(Collectors.toList()))
                .listSize(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}