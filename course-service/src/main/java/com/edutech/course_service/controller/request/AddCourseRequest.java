package com.edutech.course_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCourseRequest {
    @NotBlank(message = "El nombre del curso es obligatorio")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotBlank(message = "El código del curso es obligatorio")
    private String courseCode;

    @Positive(message = "El ID del instructor debe ser un número positivo")
    private Long instructorId;
}
