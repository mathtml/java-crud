package com.mathtml.repository.apirestjava.dto;

import com.mathtml.repository.apirestjava.model.UserAddress;

public class UserAddressResponse {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String userFullName;

    public UserAddressResponse(UserAddress address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.zipCode = address.getZipCode();
        this.userFullName = address.getUser().getFullName();
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getUserFullName() {
        return userFullName;
    }
}