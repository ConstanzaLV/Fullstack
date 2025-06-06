package com.edutech.course_service.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {
    private Long id;
    private String name;
    private String description;
    private String courseCode;
    private Long instructorId;
}