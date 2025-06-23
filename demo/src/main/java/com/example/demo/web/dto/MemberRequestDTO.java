package com.example.demo.web.dto;

import com.example.demo.domain.enums.Role;
import com.example.demo.validation.annotation.ExistCategories;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDto{
        @NotBlank
        String name;
        @NotBlank
        @Email
        String email;    // 이메일 필드 추가
        @NotBlank
        String password;    // 비밀번호 필드 추가
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;

        @NotNull
        Role role;    // 역할 필드 추가
    }
}