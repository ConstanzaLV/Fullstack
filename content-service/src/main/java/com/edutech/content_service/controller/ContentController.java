package com.edutech.content_service.controller;

import com.edutech.content_service.controller.request.AddContentRequest;
import com.edutech.content_service.service.domain.Content;
import com.edutech.content_service.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    @Autowired
    private ContentService service;

    @GetMapping
    public List<Content> getAll() {
        return service.getAllContents();
    }

    @GetMapping("/{id}")
    public Content getById(@PathVariable Long id) {
        return service.getContentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Content create(@Valid @RequestBody AddContentRequest req) {
        return service.createContent(req);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,
                       @Valid @RequestBody AddContentRequest req) {
        service.updateContent(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteContent(id);
    }
}