package com.example.demo.web.controller;

import com.example.demo.converter.MemberMissionConverter;
import com.example.demo.service.MemberMissionService.MemberMissionService;
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

            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        int pageIndex = Math.max(page - 1, 0);
        var p = service.getInProgressMissionsPage(userId, pageIndex, 10);
        return ResponseEntity.ok(MemberMissionConverter.toPageDto(p));
    }
}
