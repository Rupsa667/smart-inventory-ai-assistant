package com.example.repository;

import com.example.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByNameContainingIgnoreCase(String name);

    List<Supplier> findByEmailContainingIgnoreCase(String email);

    List<Supplier> findAllByOrderByNameAsc();
}
