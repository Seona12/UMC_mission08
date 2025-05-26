package com.example.demo.web.controller;

import com.example.demo.converter.MissionConverter;
import com.example.demo.domain.Mission;
import com.example.demo.validation.annotation.StoreExists;
import com.example.demo.web.dto.MissionPageDTO;
import com.example.demo.web.dto.MissionResponseDTO;
import com.example.demo.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/stores/{storeId}/missions")
@RequiredArgsConstructor
public class StoreMissionController {

    private final MissionService missionService;

    @GetMapping
    public ResponseEntity<MissionPageDTO> getStoreMissions(
            @PathVariable @StoreExists Long storeId,
            @RequestParam(name = "page", defaultValue = "1")
            @Parameter(example = "1", description = "1부터 시작") int page
    ) {
        int pageIndex = Math.max(page - 1, 0);
        Page<Mission> p = missionService.getMissionsByStore(storeId, pageIndex, 10);

        List<MissionResponseDTO> list = p.getContent().stream()
                .map(MissionConverter::toDto)
                .collect(Collectors.toList());

        MissionPageDTO dto = MissionPageDTO.builder()
                .missions(list)
                .listSize(p.getNumberOfElements())
                .totalPage(p.getTotalPages())
                .totalElements(p.getTotalElements())
                .isFirst(p.isFirst())
                .isLast(p.isLast())
                .build();

        return ResponseEntity.ok(dto);
    }
}

