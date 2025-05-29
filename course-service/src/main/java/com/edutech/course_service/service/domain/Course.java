package com.edutech.course_service.service.domain;

import lombok.Setter;

public class Course {
    @Setter
    private Long id;
    private String code;
    private String category;
    private String instructor;
    private String status;

    public Course(Long id,
                  String code,
                  String category,
                  String instructor,
                  String status) {
        this.id = id;
        this.code = code;
        this.category = category;
        this.instructor = instructor;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getCategory() { return category; }
    public String getInstructor() { return instructor; }
    public String getStatus() { return status; }
}