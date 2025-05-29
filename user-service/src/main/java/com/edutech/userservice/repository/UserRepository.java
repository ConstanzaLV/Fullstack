package com.edutech.userservice.repository;

import com.edutech.userservice.repository.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<UserEntity> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new UserEntity(10L, "alice", "alice@example.com", "ADMIN",   "ACTIVE"));
        users.add(new UserEntity(20L, "bob",   "bob@example.com",   "STUDENT", "ACTIVE"));
        users.add(new UserEntity(30L, "carol", "carol@example.com", "STUDENT", "INACTIVE"));
    }

    public List<UserEntity> getAll() {
        return users;
    }

    public void save(UserEntity user) {
        user.setId((users.size() + 1) * 10L);
        users.add(user);
    }

    public void replace(UserEntity found, UserEntity updated) {
        int idx = users.indexOf(found);
        updated.setId(found.getId());
        users.set(idx, updated);
    }

    public void delete(UserEntity found) {
        users.remove(found);
    }

    public UserEntity getByUsernameOrEmail(String username, String email) {
        for (UserEntity u : users) {
            if (u.getUsername().equalsIgnoreCase(username) || u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }
}
