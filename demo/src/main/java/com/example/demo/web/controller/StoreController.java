package com.example.demo.web.controller;

import com.example.demo.apiPayload.ApiResponse;
import com.example.demo.service.StoreService.StoreService;
import com.example.demo.web.dto.AddStoreRequest;
import com.example.demo.web.dto.StoreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/regions/{regionId}/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    public ApiResponse<StoreResponse> addStore(
            @PathVariable Long regionId,
            @Valid @RequestBody AddStoreRequest req) {

        // PathVariable 과 RequestBody.regionId 중복 체크도 가능
        req.setRegionId(regionId);

        Long id = storeService.addStore(req);
        return ApiResponse.onSuccess(new StoreResponse(id));
    }
}

