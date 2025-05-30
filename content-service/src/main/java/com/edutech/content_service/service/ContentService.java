package com.edutech.content_service.service;

import com.edutech.content_service.controller.request.AddContentRequest;
import com.edutech.content_service.exception.ResourceNotFoundException;
import com.edutech.content_service.repository.ContentRepository;
import com.edutech.content_service.repository.entity.ContentEntity;
import com.edutech.content_service.service.domain.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    @Autowired
    private ContentRepository repository;

    public List<Content> getAllContents() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public Content getContentById(Long id) {
        ContentEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido no encontrado con id: " + id));
        return toDomain(entity);
    }

    public Content createContent(AddContentRequest req) {
        ContentEntity saved = repository.save(
                new ContentEntity(null, req.getTitle(), req.getDescription(), req.getUrl())
        );
        return toDomain(saved);
    }

    public void updateContent(Long id, AddContentRequest req) {
        ContentEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido no encontrado con id: " + id));
        existing.setTitle(req.getTitle());
        existing.setDescription(req.getDescription());
        existing.setUrl(req.getUrl());
        repository.save(existing);
    }

    public void deleteContent(Long id) {
        ContentEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido no encontrado con id: " + id));
        repository.delete(existing);
    }

    private Content toDomain(ContentEntity e) {
        return new Content(e.getId(), e.getTitle(), e.getDescription(), e.getUrl());
    }
}