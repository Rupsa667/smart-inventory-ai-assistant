package com.example.controller;

import com.example.dto.SaleRequest;
import com.example.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
public class SaleController {
    private final SaleService saleService;

    // Add Sale
    @PostMapping
    public ResponseEntity<SaleRequest> addSale(
            @RequestBody SaleRequest saleRequest) {

        return ResponseEntity.ok(
                saleService.addSale(saleRequest)
        );
    }

    // Get All Sales
    @GetMapping
    public ResponseEntity<List<SaleRequest>> getAllSales() {

        return ResponseEntity.ok(
                saleService.getAllSales()
        );
    }

    // Get Sale By ID
    @GetMapping("/{id}")
    public ResponseEntity<SaleRequest> getSaleById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                saleService.getSaleById(id)
        );
    }

    // Update Sale
    @PutMapping("/{id}")
    public ResponseEntity<SaleRequest> updateSale(
            @PathVariable Long id,
            @RequestBody SaleRequest saleRequest) {

        return ResponseEntity.ok(
                saleService.updateSale(id, saleRequest)
        );
    }

    // Delete Sale
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSale(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                saleService.deleteSale(id)
        );
    }

    // Sales By Product
    @GetMapping("/search/product")
    public ResponseEntity<List<SaleRequest>> getSalesByProduct(
            @RequestParam Long productId) {

        return ResponseEntity.ok(
                saleService.getSalesByProduct(productId)
        );
    }

    // Sales By Date
    @GetMapping("/search/date")
    public ResponseEntity<List<SaleRequest>> getSalesByDate(
            @RequestParam LocalDate date) {

        return ResponseEntity.ok(
                saleService.getSalesByDate(date)
        );
    }

    // Sales By Date Range
    @GetMapping("/search/date-range")
    public ResponseEntity<List<SaleRequest>> getSalesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return ResponseEntity.ok(
                saleService.getSalesByDateRange(startDate, endDate)
        );
    }

    // Total Revenue
    @GetMapping("/analytics/revenue")
    public ResponseEntity<Double> getTotalRevenue() {

        return ResponseEntity.ok(
                saleService.getTotalRevenue()
        );
    }

    // Top Selling Products
    @GetMapping("/analytics/top-selling")
    public ResponseEntity<List<SaleRequest>> getTopSellingProducts() {

        return ResponseEntity.ok(
                saleService.getTopSellingProducts()
        );
    }
}
