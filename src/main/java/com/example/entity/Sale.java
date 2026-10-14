package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double totalAmount;
    private LocalDate saleDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Sale() {
    }

    public Sale(int quantity, double totalAmount,
                LocalDate saleDate, Product product) {
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
        this.product = product;
    }
}
