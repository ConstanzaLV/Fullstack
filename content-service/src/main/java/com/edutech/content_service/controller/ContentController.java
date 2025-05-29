package com.edutech.content_service.controller;


import com.edutech.content_service.controller.request.AddContentRequest;
import com.edutech.content_service.service.domain.Content;
import com.edutech.content_service.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {
    @Autowired
    private ContentService service;

    @GetMapping
    public List<Content> getContents() {
        return service.getContents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContent(@PathVariable Long id) {
        Content found = service.getContent(id);
        if (found != null) {
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
    }

    @PostMapping
    public ResponseEntity<Void> addContent(
            @Valid @RequestBody AddContentRequest request) {
        Content content = new Content(
                0L,
                request.getTitle(),
                request.getDescription(),
                request.getUrl()
        );

        boolean saved = service.save(content);
        if (saved) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateContent(
            @PathVariable Long id,
            @RequestBody Content request) {
        boolean replaced = service.replace(id, request);
        if (replaced) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}