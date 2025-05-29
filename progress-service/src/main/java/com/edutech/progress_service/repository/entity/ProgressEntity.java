package com.edutech.progress_service.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgressEntity {
    private Long id;
    private String userId;
    private String courseCode;
    private Integer progressPercentage;
    private String status;
}
