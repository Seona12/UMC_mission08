package com.example.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Builder
@Setter
@Getter @AllArgsConstructor
public class ReviewResponse {
    private Long reviewId;
    private String title;
    private String body;
    private Float score;
    private String storeName;
    private String memberName;
    private LocalDateTime createdAt;
}
