package com.example.demo.converter;

import com.example.demo.domain.Mission;
import com.example.demo.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResponseDTO toDto(Mission m) {
        return MissionResponseDTO.builder()
                .id(m.getId())
                .reward(m.getReward())
                .deadline(m.getDeadline())
                .missionSpec(m.getMissionSpec())
                .build();
    }

    public static List<MissionResponseDTO> toDtoList(List<Mission> missions) {
        return missions.stream()
                .map(MissionConverter::toDto)
                .collect(Collectors.toList());
    }
}
