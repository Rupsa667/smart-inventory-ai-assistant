package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    // Default constructor
    public Category() {
    }
    // Parameterized constructor
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
