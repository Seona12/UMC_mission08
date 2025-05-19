package com.example.demo.web.dto;

import com.example.demo.validation.annotation.MissionNotChallenged;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@MissionNotChallenged          // “이미 진행 중인지” 검증용 커스텀 애노테이션
@Getter @Setter
public class ChallengeMissionRequest {

    @NotNull(message = "memberId는 필수입니다.")
    private Long memberId;

    @NotNull(message = "missionId는 필수입니다.")
    private Long missionId;
}