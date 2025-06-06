package com.edutech.course_service.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Long id;
    private String name;
    private String description;
    private String courseCode;
    private Long instructorId;
}