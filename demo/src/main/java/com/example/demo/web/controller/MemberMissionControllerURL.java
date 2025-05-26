package com.example.demo.web.controller;

import com.example.demo.converter.MemberMissionConverter;
import com.example.demo.service.MemberMissionService.MemberMissionService;
import com.example.demo.web.annotation.PageParam;            // 올바른 패키지 import
import com.example.demo.web.dto.InProgressMissionPageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/missions/in-progress")
@RequiredArgsConstructor
@Validated
public class MemberMissionControllerURL {

    private final MemberMissionService service;

    @GetMapping
    @Operation(summary = "유저 진행 중인 미션 페이징 조회")
    public ResponseEntity<InProgressMissionPageDTO> getInProgressMissions(
            @Parameter(description = "유저 ID", example = "1", required = true)
            @PathVariable Long userId,

            @Parameter
            @PageParam                                              // ← 여기만!
            int page                                           // resolver가 1→0, 0 이하면 예외
    ) {
        var p = service.getInProgressMissionsPage(userId, page, 10);
        return ResponseEntity.ok(MemberMissionConverter.toPageDto(p));
    }
}
