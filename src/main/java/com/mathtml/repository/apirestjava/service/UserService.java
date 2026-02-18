package com.mathtml.repository.apirestjava.service;

import com.mathtml.repository.apirestjava.model.User;
import com.mathtml.repository.apirestjava.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mathtml.repository.apirestjava.exceptions.BusinessException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new BusinessException(
                    409,
                    "email_already_registered",
                    "Email already exists"
            );
        }

        if (repository.findByDocumentNumber(user.getDocumentNumber()).isPresent()) {
            throw new BusinessException(
                    409,
                    "document_already_registered",
                    "Document already exists"
            );
        }

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setFgInactive(false);

        return repository.save(user);
    }

    public List<User> getAllActiveUsers() {
        return repository.findAll()
                .stream()
                .filter(u -> !u.getFgInactive())
                .toList();
    }

    @Transactional
    public void deactivateUser(UUID userId) {
        Optional<User> userOpt = repository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setFgInactive(true);
            repository.save(user);
        }
    }

    public void deleteUser(UUID userId) {
        repository.deleteById(userId);
    }

    @Transactional
    public User updateUser(User updatedUser) {
        Optional<User> userOpt = repository.findById(updatedUser.getUserId());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setFullName(updatedUser.getFullName());
            user.setBirthDate(updatedUser.getBirthDate());
            user.setDocumentNumber(updatedUser.getDocumentNumber());
            user.setEmail(updatedUser.getEmail());
            user.setUpdatedAt(LocalDateTime.now());
            return repository.save(user);
        }
        return null;
    }
}