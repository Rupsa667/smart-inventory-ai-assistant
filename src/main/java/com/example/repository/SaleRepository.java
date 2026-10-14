package com.example.repository;

import com.example.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

    public interface SaleRepository extends JpaRepository<Sale, Long> {

        List<Sale> findByProductId(Long productId);

        List<Sale> findBySaleDate(LocalDate date);

        List<Sale> findBySaleDateBetween(
                LocalDate startDate,
                LocalDate endDate);

        List<Sale> findAllByOrderByQuantityDesc();
    }

