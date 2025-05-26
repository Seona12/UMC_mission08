package com.example.demo.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionResponseDTO {
    private Long id;
    private Integer reward;
    private LocalDate deadline;
    private String missionSpec;
}
//확인용