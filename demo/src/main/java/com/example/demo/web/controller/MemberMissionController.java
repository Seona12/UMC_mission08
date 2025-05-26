package com.example.demo.web.controller;

import com.example.demo.apiPayload.ApiResponse;
import com.example.demo.converter.MemberMissionConverter;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.service.MemberMissionService.MemberMissionService;
import com.example.demo.validation.annotation.UserExists;
import com.example.demo.web.dto.ChallengeMissionRequest;
import com.example.demo.web.dto.ChallengeMissionResponse;
import com.example.demo.web.dto.InProgressMissionPageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionService service;

    @PostMapping("/challenge")
    public ApiResponse<ChallengeMissionResponse> challenge(
            @Valid @RequestBody ChallengeMissionRequest req
    ) {
        Long id = service.challenge(req);
        return ApiResponse.onSuccess(new ChallengeMissionResponse(id));
    }

}
