package com.mathtml.repository.apirestjava.controllers;

import com.mathtml.repository.apirestjava.model.User;
import com.mathtml.repository.apirestjava.model.UserAddress;
import com.mathtml.repository.apirestjava.repository.UserAddressRepository;
import com.mathtml.repository.apirestjava.repository.UserRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/addresses")
public class UserAddressController {

    private final UserAddressRepository addressRepository;
    private final UserRepository userRepository;

    public UserAddressController(UserAddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public UserAddress createAddress(@PathVariable UUID userId, @RequestBody UserAddress address) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        address.setUser(user);
        return addressRepository.save(address);
    }

    @GetMapping
    public List<UserAddress> getUserAddresses(@PathVariable UUID userId) {
        return addressRepository.findByUser_UserId(userId);
    }
}