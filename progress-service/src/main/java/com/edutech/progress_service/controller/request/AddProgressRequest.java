package com.edutech.progress_service.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProgressRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String courseCode;
    @NotNull
    @Min(0)
    private Integer progressPercentage;
    @NotBlank
    private String status;
}
