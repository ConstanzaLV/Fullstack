package com.edutech.userservice.service;

import com.edutech.userservice.repository.UserRepository;
import com.edutech.userservice.repository.entity.UserEntity;
import com.edutech.userservice.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        for (UserEntity e : repository.getAll()) {
            result.add(new User(
                    e.getId(),
                    e.getUsername(),
                    e.getEmail(),
                    e.getRole(),
                    e.getStatus()
            ));
        }
        return result;
    }

    public User getUser(Long id) {
        for (UserEntity e : repository.getAll()) {
            if (e.getId().equals(id)) {
                return new User(
                        e.getId(),
                        e.getUsername(),
                        e.getEmail(),
                        e.getRole(),
                        e.getStatus()
                );
            }
        }
        return null;
    }

    public boolean save(User u) {
        UserEntity existing = repository.getByUsernameOrEmail(
                u.getUsername(),
                u.getEmail()
        );
        if (existing == null) {
            UserEntity e = new UserEntity(
                    u.getId(),
                    u.getUsername(),
                    u.getEmail(),
                    u.getRole(),
                    u.getStatus()
            );
            repository.save(e);
            u.setId(e.getId());
            return true;
        }
        return false;
    }

    public boolean replace(Long id, User u) {
        UserEntity found = null;
        for (UserEntity e : repository.getAll()) {
            if (e.getId().equals(id)) {
                found = e;
                break;
            }
        }
        if (found != null) {
            UserEntity updated = new UserEntity(
                    id,
                    u.getUsername(),
                    u.getEmail(),
                    u.getRole(),
                    u.getStatus()
            );
            repository.replace(found, updated);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        UserEntity found = null;
        for (UserEntity e : repository.getAll()) {
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