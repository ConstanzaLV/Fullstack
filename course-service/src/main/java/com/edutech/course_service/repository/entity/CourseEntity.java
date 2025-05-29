package com.edutech.course_service.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {
    private Long id;
    private String code;
    private String category;
    private String instructor;
    private String status;
}
