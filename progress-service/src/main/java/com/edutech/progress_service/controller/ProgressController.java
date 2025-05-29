package com.edutech.progress_service.controller;

import com.edutech.progress_service.controller.request.AddProgressRequest;
import com.edutech.progress_service.service.domain.Progress;
import com.edutech.progress_service.service.ProgressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {
    @Autowired
    private ProgressService service;

    @GetMapping
    public List<Progress> getProgress() {
        return service.getProgresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Progress> getProgress(@PathVariable Long id) {
        Progress found = service.getProgress(id);
        if (found != null) {
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Void> addProgress(@Valid @RequestBody AddProgressRequest request) {
        Progress progress = new Progress(
                0L,
                request.getUserId(),
                request.getCourseCode(),
                request.getProgressPercentage(),
                request.getStatus()
        );
        boolean saved = service.save(progress);
        if (saved) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProgress(
            @PathVariable Long id,
            @RequestBody Progress request
    ) {
        boolean replaced = service.replace(id, request);
        if (replaced) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
