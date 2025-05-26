package com.example.demo.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class InProgressMissionDTO {
    private Long missionId;
    private String missionSpec;
    private Integer reward;
    private LocalDate deadline;
    private LocalDate createdAt;   // MemberMission 에 시작일이 있다면
}
