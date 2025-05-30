package com.edutech.progress_service.controller;

import com.edutech.progress_service.controller.request.AddProgressRequest;
import com.edutech.progress_service.service.domain.Progress;
import com.edutech.progress_service.service.ProgressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService service;

    @GetMapping
    public List<Progress> getAll() {
        return service.getAllProgressRecords();
    }

    @GetMapping("/{id}")
    public Progress getById(@PathVariable Long id) {
        return service.getProgressById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Progress create(@Valid @RequestBody AddProgressRequest req) {
        return service.createProgress(req);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,
                       @Valid @RequestBody AddProgressRequest req) {
        service.updateProgress(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteProgress(id);
    }
}
