package com.edutech.content_service.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentEntity {
    private Long id;
    private String title;
    private String description;
    private String url;
}