package com.example.demo.web.controller;

import com.example.demo.apiPayload.code.ErrorReasonDTO;
import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.converter.MissionConverter;
import com.example.demo.domain.Mission;
import com.example.demo.validation.annotation.StoreExists;
import com.example.demo.web.annotation.PageParam;
import com.example.demo.web.dto.MissionPageDTO;
import com.example.demo.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stores/{storeId}/missions")
@RequiredArgsConstructor
public class StoreMissionController {

    private final MissionService missionService;

    @GetMapping
    @Operation(
            summary     = "가게별 미션 페이징 조회",
            description = "storeId에 해당하는 미션을 한 페이지에 10개씩 조회합니다.",
            responses   = {
                    @ApiResponse(responseCode = "200", description = "조회 성공"),
                    @ApiResponse(
                            responseCode = "400",
                            description  = "STORE4001: 해당 가게를 찾을 수 없습니다.",
                            content      = @Content(schema = @Schema(implementation = ErrorReasonDTO.class))
                    )
            }
    )
    public ResponseEntity<MissionPageDTO> getStoreMissions(
            @Parameter(description = "가게 ID", example = "1", required = true)
            @PathVariable @StoreExists Long storeId,

            @Parameter(
                    name        = "page",
                    in          = ParameterIn.QUERY,
                    description = "페이지 번호 (1부터 시작)",
                    example     = "1",
                    required    = true
            )
            @PageParam
            int page  // resolver가 1→0, 0 이하→PageException(400)
    ) {
        Page<Mission> p = missionService.getMissionsByStore(storeId, page, 10);

        MissionPageDTO dto = MissionPageDTO.builder()
                .missions(p.getContent()
                        .stream()
                        .map(MissionConverter::toDto)
                        .collect(Collectors.toList()))
                .listSize(p.getNumberOfElements())
                .totalPage(p.getTotalPages())
                .totalElements(p.getTotalElements())
                .isFirst(p.isFirst())
                .isLast(p.isLast())
                .build();

        return ResponseEntity.ok(dto);
    }
}
