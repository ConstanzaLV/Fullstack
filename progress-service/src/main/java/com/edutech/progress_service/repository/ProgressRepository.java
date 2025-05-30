package com.edutech.progress_service.repository;

import com.edutech.progress_service.repository.entity.ProgressEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProgressRepository {

    private final List<ProgressEntity> records = new ArrayList<>();

    public ProgressRepository() {
        records.add(new ProgressEntity(1L, 10L, 50));
        records.add(new ProgressEntity(2L, 20L, 75));
    }

    public List<ProgressEntity> findAll() {
        return new ArrayList<>(records);
    }

    public Optional<ProgressEntity> findById(Long id) {
        return records.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public ProgressEntity save(ProgressEntity entity) {
        if (entity.getId() == null) {
            long nextId = records.stream()
                    .mapToLong(ProgressEntity::getId)
                    .max()
                    .orElse(0L) + 1;
            entity.setId(nextId);
            records.add(entity);
        } else {
            records.removeIf(r -> r.getId().equals(entity.getId()));
            records.add(entity);
        }
        return entity;
    }

    public void delete(ProgressEntity entity) {
        records.removeIf(r -> r.getId().equals(entity.getId()));
    }
}
