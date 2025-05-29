package com.edutech.content_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddContentRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String url;
}
