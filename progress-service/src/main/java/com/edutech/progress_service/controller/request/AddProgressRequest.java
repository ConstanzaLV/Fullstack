package com.edutech.progress_service.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddProgressRequest {
    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser un número positivo")
    private Long userId;

    @Positive(message = "El ID del curso debe ser un número positivo")
    private Long courseId;

    @Positive(message = "El ID del módulo debe ser un número positivo")
    private Long moduleId;

    @NotNull(message = "El porcentaje es obligatorio")
    @Positive(message = "El porcentaje debe ser un valor positivo")
    private Integer percentage;


}
