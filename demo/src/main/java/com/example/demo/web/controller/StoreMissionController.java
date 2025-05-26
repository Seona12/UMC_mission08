package com.example.demo.web.controller;

import com.example.demo.converter.MissionConverter;
import com.example.demo.web.dto.MissionResponseDTO;
import com.example.demo.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/stores/{storeId}/missions")
@RequiredArgsConstructor
public class StoreMissionController {

    private final MissionService missionService;

    @GetMapping
    @Operation(
            summary     = "가게별 미션 목록 조회",
            description = "storeId에 속한 모든 미션을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "STORE4001: 해당 가게를 찾을 수 없습니다.")
    })
    public ResponseEntity<List<MissionResponseDTO>> getStoreMissions(
            @Parameter(description = "가게 ID", example = "3", required = true)
            @PathVariable Long storeId
    ) {
        // @StoreExists 검증을 원하시면, PathVariable 선언에 @StoreExists Long storeId 로 변경하세요
        List<MissionResponseDTO> dtos =
                MissionConverter.toDtoList(missionService.getMissionsByStore(storeId));
        return ResponseEntity.ok(dtos);
    }
}

