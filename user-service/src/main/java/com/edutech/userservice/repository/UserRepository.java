package com.edutech.userservice.repository;

import com.edutech.userservice.repository.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<UserEntity> users = new ArrayList<>();

    public UserRepository() {
        users.add(new UserEntity(1L, "Alice", "alice@example.com"));
        users.add(new UserEntity(2L, "Bob",   "bob@example.com"));
    }

    public List<UserEntity> findAll() {
        return new ArrayList<>(users);
    }

    public Optional<UserEntity> findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public UserEntity save(UserEntity entity) {
        if (entity.getId() == null) {
            long next = users.stream()
                    .mapToLong(UserEntity::getId)
                    .max()
                    .orElse(0L) + 1;
            entity.setId(next);
            users.add(entity);
        } else {
            users.removeIf(u -> u.getId().equals(entity.getId()));
            users.add(entity);
        }
        return entity;
    }

    public void delete(UserEntity entity) {
        users.removeIf(u -> u.getId().equals(entity.getId()));
    }

    public Optional<UserEntity> findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}
