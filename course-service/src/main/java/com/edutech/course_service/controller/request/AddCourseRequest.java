package com.edutech.course_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCourseRequest {
    @NotBlank
    private String code;
    @NotBlank
    private String category;
    @NotBlank
    private String instructor;
    @NotBlank
    private String status;
}
