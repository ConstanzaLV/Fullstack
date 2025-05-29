package com.edutech.course_service.controller;

import com.edutech.course_service.controller.request.AddCourseRequest;
import com.edutech.course_service.service.domain.Course;
import com.edutech.course_service.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService service;

    @GetMapping
    public List<Course> getCourses() {
        return service.getCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Course found = service.getCourse(id);
        if (found != null) {
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Void> addCourse(@Valid @RequestBody AddCourseRequest request) {
        Course course = new Course(
                0L,
                request.getCode(),
                request.getCategory(),
                request.getInstructor(),
                request.getStatus()
        );
        boolean saved = service.save(course);
        if (saved) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourse(
            @PathVariable Long id,
            @RequestBody Course request) {

        Course course = new Course(
                0L,
                request.getCode(),
                request.getCategory(),
                request.getInstructor(),
                request.getStatus()
        );
        boolean replaced = service.replace(id, course);
        if (replaced) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
