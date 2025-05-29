package com.edutech.progress_service.repository;

import com.edutech.progress_service.repository.entity.ProgressEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProgressRepository {
    private final List<ProgressEntity> progresses;

    public ProgressRepository() {
        progresses = new ArrayList<>();
        // ejemplos precargados
        progresses.add(new ProgressEntity(10L, "user1", "CS101", 20, "IN_PROGRESS"));
        progresses.add(new ProgressEntity(20L, "user2", "SB202", 100, "COMPLETED"));
    }

    public List<ProgressEntity> getAll() {
        return progresses;
    }

    public void save(ProgressEntity p) {
        p.setId((progresses.size() + 1) * 10L);
        progresses.add(p);
    }

    public void replace(ProgressEntity found, ProgressEntity updated) {
        int idx = progresses.indexOf(found);
        updated.setId(found.getId());
        progresses.set(idx, updated);
    }

    public void delete(ProgressEntity found) {
        progresses.remove(found);
    }

    public ProgressEntity getByUserAndCourse(String userId, String courseCode) {
        for (ProgressEntity p : progresses) {
            if (p.getUserId().equals(userId) && p.getCourseCode().equals(courseCode)) {
                return p;
            }
        }
        return null;
    }
}
