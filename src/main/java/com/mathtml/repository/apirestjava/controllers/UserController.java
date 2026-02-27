package com.mathtml.repository.apirestjava.controllers;

import com.mathtml.repository.apirestjava.dto.ApiResponse;
import com.mathtml.repository.apirestjava.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.mathtml.repository.apirestjava.model.User;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping
    public List<User> list() {
        return service.getAllActiveUsers();
    }

    @PutMapping("/{id}/deactivate")
    public ApiResponse deactivate(@PathVariable UUID id) {
        return service.deactivateUser(id);
    }

    @PutMapping("/{id}/activate")
    public ApiResponse activate(@PathVariable UUID id) {
        return service.activateUser(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody User user) {
        user.setUserId(id);
        return service.updateUser(user);
    }
}