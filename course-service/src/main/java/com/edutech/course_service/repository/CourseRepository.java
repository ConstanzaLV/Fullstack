package com.edutech.course_service.repository;

import com.edutech.course_service.repository.entity.CourseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private final List<CourseEntity> courses;

    public CourseRepository() {
        courses = new ArrayList<>();
        courses.add(new CourseEntity(10L, "CS101", "Programming", "Alicia Rojas", "ARCHIVED"));
        courses.add(new CourseEntity(20L, "SB202", "Spring Boot", "Carlos Hernandez", "PUBLISHED"));
        courses.add(new CourseEntity(30L, "API303", "APIs", "Carolina Muñoz", "ARCHIVED"));
    }

    public List<CourseEntity> getAll() {
        return courses;
    }

    public void save(CourseEntity course) {
        course.setId((courses.size() + 1) * 10L);
        courses.add(course);
    }

    public void replace(CourseEntity found, CourseEntity updated) {
        int idx = courses.indexOf(found);
        updated.setId(found.getId());
        courses.set(idx, updated);
    }

    public void delete(CourseEntity found) {
        courses.remove(found);
    }

    public CourseEntity getByCodeOrInstructor(String code, String instructor) {
        for (CourseEntity c : courses) {
            if (c.getCode().equals(code) || c.getInstructor().equals(instructor)) {
                return c;
            }
        }
        return null;
    }
}
