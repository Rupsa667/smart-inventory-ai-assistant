package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierRequest {
    private String name;
    private String email;
    private String phone;
    private String address;

    public SupplierRequest() {
    }

    public SupplierRequest(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
