package com.edutech.progress_service.service;

import com.edutech.progress_service.controller.request.AddProgressRequest;
import com.edutech.progress_service.exception.ResourceNotFoundException;
import com.edutech.progress_service.repository.ProgressRepository;
import com.edutech.progress_service.repository.entity.ProgressEntity;
import com.edutech.progress_service.service.domain.Progress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository repository;

    public List<Progress> getAllProgressRecords() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public Progress getProgressById(Long id) {
        ProgressEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de progreso no encontrado con id: " + id));
        return toDomain(entity);
    }

    public Progress createProgress(AddProgressRequest req) {
        ProgressEntity saved = repository.save(
                new ProgressEntity(null, req.getUserId(), req.getCourseId(), req.getModuleId(), req.getPercentage())
        );
        return toDomain(saved);
    }

    public void updateProgress(Long id, AddProgressRequest req) {
        ProgressEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de progreso no encontrado con id: " + id));
        existing.setUserId(req.getUserId());
        existing.setCourseId(req.getCourseId());
        existing.setModuleId(req.getModuleId());
        existing.setPercentage(req.getPercentage());
        repository.save(existing);
    }

    public void deleteProgress(Long id) {
        ProgressEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de progreso no encontrado con id: " + id));
        repository.delete(existing);
    }

    private Progress toDomain(ProgressEntity e) {
        return new Progress(e.getId(), e.getUserId(), e.getCourseId(), e.getModuleId(), e.getPercentage());
    }
}