package com.mathtml.repository.apirestjava.repository;


import com.mathtml.repository.apirestjava.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserAddressRepository extends JpaRepository<UserAddress, UUID> {

    List<UserAddress> findByUser_UserId(UUID userId);

}