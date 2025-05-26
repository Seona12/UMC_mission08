package com.example.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionPageDTO {
    private List<MissionResponseDTO> missions;
    private Integer listSize;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;
}
