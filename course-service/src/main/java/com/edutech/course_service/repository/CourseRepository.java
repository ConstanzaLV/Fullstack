package com.edutech.course_service.repository;

import com.edutech.course_service.repository.entity.CourseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final List<CourseEntity> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new CourseEntity(1L, "Java Básico", "Introducción a Java"));
        courses.add(new CourseEntity(2L, "Spring Boot", "Desarrollo con Spring Boot"));
    }

    public List<CourseEntity> findAll() {
        return new ArrayList<>(courses);
    }

    public Optional<CourseEntity> findById(Long id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public CourseEntity save(CourseEntity entity) {
        if (entity.getId() == null) {
            long nextId = courses.stream()
                    .mapToLong(CourseEntity::getId)
                    .max()
                    .orElse(0L) + 1;
            entity.setId(nextId);
            courses.add(entity);
        } else {
            courses.removeIf(c -> c.getId().equals(entity.getId()));
            courses.add(entity);
        }
        return entity;
    }

    public void delete(CourseEntity entity) {
        courses.removeIf(c -> c.getId().equals(entity.getId()));
    }
}
