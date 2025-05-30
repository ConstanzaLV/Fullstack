package com.edutech.userservice.service;

import com.edutech.userservice.controller.request.AddUserRequest;
import com.edutech.userservice.exception.ResourceNotFoundException;
import com.edutech.userservice.repository.UserRepository;
import com.edutech.userservice.repository.entity.UserEntity;
import com.edutech.userservice.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return toDomain(entity);
    }

    public User createUser(AddUserRequest req) {
        UserEntity saved = repository.save(
                new UserEntity(null, req.getName(), req.getEmail())
        );
        return toDomain(saved);
    }

    public void updateUser(Long id, AddUserRequest req) {
        UserEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        existing.setName(req.getName());
        existing.setEmail(req.getEmail());
        repository.save(existing);
    }

    public void deleteUser(Long id) {
        UserEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        repository.delete(existing);
    }

    private User toDomain(UserEntity e) {
        return new User(e.getId(), e.getName(), e.getEmail());
    }
}