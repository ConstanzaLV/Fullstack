package com.edutech.progress_service.service;

import com.edutech.progress_service.repository.ProgressRepository;
import com.edutech.progress_service.repository.entity.ProgressEntity;
import com.edutech.progress_service.service.domain.Progress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository repository;

    public List<Progress> getProgresses() {
        List<Progress> result = new ArrayList<>();
        for (ProgressEntity e : repository.getAll()) {
            result.add(new Progress(
                    e.getId(),
                    e.getUserId(),
                    e.getCourseCode(),
                    e.getProgressPercentage(),
                    e.getStatus()
            ));
        }
        return result;
    }

    public Progress getProgress(Long id) {
        for (ProgressEntity e : repository.getAll()) {
            if (e.getId().equals(id)) {
                return new Progress(
                        e.getId(),
                        e.getUserId(),
                        e.getCourseCode(),
                        e.getProgressPercentage(),
                        e.getStatus()
                );
            }
        }
        return null;
    }

    public boolean save(Progress p) {
        ProgressEntity existing = repository.getByUserAndCourse(p.getUserId(), p.getCourseCode());
        if (existing == null) {
            ProgressEntity e = new ProgressEntity(
                    p.getId(),
                    p.getUserId(),
                    p.getCourseCode(),
                    p.getProgressPercentage(),
                    p.getStatus()
            );
            repository.save(e);
            p.setId(e.getId());
            return true;
        }
        return false;
    }

    public boolean replace(Long id, Progress p) {
        ProgressEntity found = null;
        for (ProgressEntity e : repository.getAll()) {
            if (e.getId().equals(id)) {
                found = e;
                break;
            }
        }
        if (found != null) {
            ProgressEntity updated = new ProgressEntity(
                    id,
                    p.getUserId(),
                    p.getCourseCode(),
                    p.getProgressPercentage(),
                    p.getStatus()
            );
            repository.replace(found, updated);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        ProgressEntity found = null;
        for (ProgressEntity e : repository.getAll()) {
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
