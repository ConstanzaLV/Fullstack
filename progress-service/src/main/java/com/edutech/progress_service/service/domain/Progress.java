package com.edutech.progress_service.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Progress {
    private Long id;
    private Long userId;
    private Long courseId;
    private Long moduleId;
    private Integer percentage;
}