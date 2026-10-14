package com.example.service;

import com.example.dto.SaleRequest;

import java.time.LocalDate;
import java.util.List;

public interface SaleService {

    SaleRequest addSale(SaleRequest saleRequest);
    List<SaleRequest> getAllSales();
    SaleRequest getSaleById(Long id);
    SaleRequest updateSale(Long id, SaleRequest saleRequest);
    String deleteSale(Long id);
    List<SaleRequest> getSalesByProduct(Long productId);
    List<SaleRequest> getSalesByDate(LocalDate date);
    List<SaleRequest> getSalesByDateRange(
            LocalDate startDate,
            LocalDate endDate);

    double getTotalRevenue();

    List<SaleRequest> getTopSellingProducts();
}
