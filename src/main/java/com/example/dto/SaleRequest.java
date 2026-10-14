package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaleRequest {
    private int quantity;
    private double totalAmount;
    private LocalDate saleDate;
    private Long productId;

    public SaleRequest() {
    }

    public SaleRequest(int quantity, double totalAmount,
                       LocalDate saleDate, Long productId) {
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
        this.productId = productId;
    }
}