package com.example.demo.service.MemberService;

// src/main/java/com/example/demo/service/MemberService/MemberCommandService.java

import com.example.demo.domain.Member;
import com.example.demo.web.dto.MemberRequestDTO.JoinDto;

public interface MemberCommandService {
    /**
     * 신규 회원 가입 처리
     *
     * @param request JoinDto 에 담긴 가입 정보
     * @return 저장된 Member 엔티티
     */
    Member joinMember(JoinDto request);
}
