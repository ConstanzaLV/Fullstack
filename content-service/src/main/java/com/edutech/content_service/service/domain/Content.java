package com.edutech.content_service.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private Long id;
    private String title;
    private String description;
    private String url;
}

