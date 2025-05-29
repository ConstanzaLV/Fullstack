package com.edutech.content_service.service.domain;

import lombok.Setter;

public class Content {
    @Setter
    private Long id;
    private String title;
    private String description;
    private String url;

    public Content(Long id, String title, String description, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

}

