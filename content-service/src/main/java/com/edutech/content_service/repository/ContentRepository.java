package com.edutech.content_service.repository;

import com.edutech.content_service.repository.entity.ContentEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContentRepository {
    private final List<ContentEntity> contents;

    public ContentRepository() {
        contents = new ArrayList<>();
        contents.add(new ContentEntity(10L, "Introducción a Java", "Conceptos básicos de Java", "https://example.com/java-intro"));
        contents.add(new ContentEntity(20L, "Guía de Spring Boot", "Tutorial rápido de Spring Boot", "https://example.com/spring-boot"));
        contents.add(new ContentEntity(30L, "APIs REST con Spring", "Cómo crear APIs RESTful con Spring", "https://example.com/spring-rest"));
    }

    public List<ContentEntity> getAll() {
        return contents;
    }


    public void save(ContentEntity content) {
        content.setId((contents.size() + 1)*10L);
        contents.add(content);
    }

    public void replace(ContentEntity found, ContentEntity newContent) {
        int idx = contents.indexOf(found);
        newContent.setId(found.getId());
        contents.set(idx, newContent);
    }

    public void delete(ContentEntity found) {
        contents.remove(found);
    }

    public ContentEntity getByTitleOrUrl(String title, String url) {
        for (ContentEntity c : contents) {
            if (c.getTitle().equals(title) || c.getUrl().equals(url)) {
                return c;
            }
        }
        return null;
    }
}
