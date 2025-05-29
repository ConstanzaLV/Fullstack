package com.edutech.progress_service.service.domain;

import lombok.Setter;

public class Progress {
    @Setter
    private Long id;
    private String userId;
    private String courseCode;
    private Integer progressPercentage;
    private String status;

    public Progress(Long id, String userId, String courseCode, Integer progressPercentage, String status) {
        this.id = id;
        this.userId = userId;
        this.courseCode = courseCode;
        this.progressPercentage = progressPercentage;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getUserId() { return userId; }
    public String getCourseCode() { return courseCode; }
    public Integer getProgressPercentage() { return progressPercentage; }
    public String getStatus() { return status; }
}
