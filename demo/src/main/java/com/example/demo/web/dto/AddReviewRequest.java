package com.example.demo.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddReviewRequest {

    @NotNull(message = "memberId는 필수입니다.")
    private Long memberId;

    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotNull(message = "score는 필수입니다.")
    @DecimalMin(value = "0.0", inclusive = true, message = "score는 0 이상이어야 합니다.")
    @DecimalMax(value = "5.0", inclusive = true, message = "score는 5 이하이어야 합니다.")
    private Float score;
}