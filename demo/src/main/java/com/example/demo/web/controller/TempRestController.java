package com.example.demo.web.controller;

import com.example.demo.apiPayload.ApiResponse;
import com.example.demo.apiPayload.code.ReasonDTO;
import com.example.demo.converter.TempConverter;
import com.example.demo.service.TempService.TempQueryService;
import com.example.demo.web.dto.TempResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.apiPayload.ApiResponse;
import com.example.demo.apiPayload.code.status.SuccessStatus;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){
        TempResponse.TempTestDTO dto = TempConverter.toTempTestDTO();
        // 기본 OK 를 쓰실 땐 onSuccess(dto)
        return ApiResponse.onSuccess(dto);
        // 만약 커스텀 성공 코드를 쓰고 싶다면 of(...)
        // return ApiResponse.of(SuccessStatus._OK, dto);
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}
