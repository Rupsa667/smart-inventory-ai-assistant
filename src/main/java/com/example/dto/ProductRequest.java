package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String brand;
    private double price;
    private Long categoryId;
    private Long supplierId;
    public ProductRequest() {
    }

    public ProductRequest(String name, String brand, double price,
                          Long categoryId, Long supplierId) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
    }
}
