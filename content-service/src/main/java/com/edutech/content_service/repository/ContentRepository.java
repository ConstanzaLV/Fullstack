package com.edutech.content_service.repository;

import com.edutech.content_service.repository.entity.ContentEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentRepository {

    private final List<ContentEntity> contents = new ArrayList<>();

    public ContentRepository() {
        // Ejemplo de datos iniciales
        contents.add(new ContentEntity(1L, "Intro Java", "Básicos de Java", "https://ex.com/java"));
        contents.add(new ContentEntity(2L, "Spring Boot", "Tutorial Spring Boot", "https://ex.com/spring"));
    }

    public List<ContentEntity> findAll() {
        return new ArrayList<>(contents);
    }

    public Optional<ContentEntity> findById(Long id) {
        return contents.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public ContentEntity save(ContentEntity entity) {
        if (entity.getId() == null) {
            long nextId = contents.stream()
                    .mapToLong(ContentEntity::getId)
                    .max()
                    .orElse(0L) + 1;
            entity.setId(nextId);
            contents.add(entity);
        } else {
            contents.removeIf(c -> c.getId().equals(entity.getId()));
            contents.add(entity);
        }
        return entity;
    }

    public void delete(ContentEntity entity) {
        contents.removeIf(c -> c.getId().equals(entity.getId()));
    }
}
