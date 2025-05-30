package com.edutech.course_service.service;

import com.edutech.course_service.controller.request.AddCourseRequest;
import com.edutech.course_service.exception.ResourceNotFoundException;
import com.edutech.course_service.repository.CourseRepository;
import com.edutech.course_service.repository.entity.CourseEntity;
import com.edutech.course_service.service.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public List<Course> getAllCourses() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public Course getCourseById(Long id) {
        CourseEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + id));
        return toDomain(entity);
    }

    public Course createCourse(AddCourseRequest req) {
        CourseEntity saved = repository.save(
                new CourseEntity(null, req.getName(), req.getDescription(), req.getCourseCode(),
                        req.getInstructorId())
        );
        return toDomain(saved);
    }

    public void updateCourse(Long id, AddCourseRequest req) {
        CourseEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + id));
        existing.setName(req.getName());
        existing.setDescription(req.getDescription());
        existing.setCourseCode(req.getCourseCode());
        existing.setInstructorId(req.getInstructorId());
        repository.save(existing);
    }

    public void deleteCourse(Long id) {
        CourseEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + id));
        repository.delete(existing);
    }

    private Course toDomain(CourseEntity e) {
        return new Course(e.getId(), e.getName(), e.getDescription(), e.getCourseCode(),
                e.getInstructorId());
    }
}
