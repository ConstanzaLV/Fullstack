package com.edutech.course_service.service;

import com.edutech.course_service.repository.CourseRepository;
import com.edutech.course_service.repository.entity.CourseEntity;
import com.edutech.course_service.service.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;

    public List<Course> getCourses() {
        List<CourseEntity> entities = repository.getAll();
        List<Course> result = new ArrayList<>();
        for (CourseEntity e : entities) {
            result.add(new Course(
                    e.getId(),
                    e.getCode(),
                    e.getCategory(),
                    e.getInstructor(),
                    e.getStatus()
            ));
        }
        return result;
    }

    public Course getCourse(Long id) {
        for (CourseEntity e : repository.getAll()) {
            if (e.getId().equals(id)) {
                return new Course(
                        e.getId(),
                        e.getCode(),
                        e.getCategory(),
                        e.getInstructor(),
                        e.getStatus()
                );
            }
        }
        return null;
    }

    public boolean save(Course course) {
        CourseEntity existing = repository.getByCodeOrInstructor(
                course.getCode(),
                course.getInstructor()
        );
        if (existing == null) {
            CourseEntity e = new CourseEntity(
                    course.getId(),
                    course.getCode(),
                    course.getCategory(),
                    course.getInstructor(),
                    course.getStatus()
            );
            repository.save(e);
            course.setId(e.getId());
            return true;
        }
        return false;
    }

    public boolean replace(Long id, Course course) {
        CourseEntity found = null;
        for (CourseEntity e : repository.getAll()) {
            if (e.getId().equals(id)) {
                found = e;
                break;
            }
        }
        if (found != null) {
            CourseEntity updated = new CourseEntity(
                    id,
                    course.getCode(),
                    course.getCategory(),
                    course.getInstructor(),
                    course.getStatus()
            );
            repository.replace(found, updated);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        CourseEntity found = null;
        for (CourseEntity e : repository.getAll()) {
            if (e.getId().equals(id)) {
                found = e;
                break;
            }
        }
        if (found != null) {
            repository.delete(found);
            return true;
        }
        return false;
    }
}
