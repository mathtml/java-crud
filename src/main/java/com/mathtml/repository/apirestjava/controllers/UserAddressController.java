package com.mathtml.repository.apirestjava.controllers;

import com.mathtml.repository.apirestjava.model.User;
import com.mathtml.repository.apirestjava.model.UserAddress;
import com.mathtml.repository.apirestjava.repository.UserAddressRepository;
import com.mathtml.repository.apirestjava.repository.UserRepository;
import com.mathtml.repository.apirestjava.dto.UserAddressResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public UserAddressResponse createAddress(@PathVariable UUID userId, @RequestBody UserAddress address) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        address.setUser(user);
        UserAddress savedAddress = addressRepository.save(address);

        return new UserAddressResponse(savedAddress);
    }

    @GetMapping
    public List<UserAddressResponse> getUserAddresses(@PathVariable UUID userId) {
        List<UserAddress> addresses = addressRepository.findByUser_UserId(userId);

        return addresses.stream()
                .map(UserAddressResponse::new)
                .collect(Collectors.toList());
    }
}