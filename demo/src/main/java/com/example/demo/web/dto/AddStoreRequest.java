package com.example.demo.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStoreRequest {
    @NotBlank
    private String name;
    @NotBlank private String address;
    @NotBlank private String phone;
    @NotNull  private Long regionId;
}
