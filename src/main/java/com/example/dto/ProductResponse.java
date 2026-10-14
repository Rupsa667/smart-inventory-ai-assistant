package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private String brand;
    private double price;
    private Long categoryId;
    private Long supplierId;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, String brand,
                           double price, Long categoryId, Long supplierId) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
    }
}
