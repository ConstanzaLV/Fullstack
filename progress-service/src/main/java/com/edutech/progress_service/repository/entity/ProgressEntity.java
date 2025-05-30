package com.edutech.progress_service.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressEntity {
    private Long id;
    private Long userId;
    private Integer percentage;
}