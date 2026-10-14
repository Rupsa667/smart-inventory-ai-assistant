package com.example.service.impl;

import com.example.config.Converter;
import com.example.dto.SaleRequest;
import com.example.entity.Inventory;
import com.example.entity.Product;
import com.example.entity.Sale;
import com.example.exception.InventoryNotFoundException;
import com.example.exception.ProductNotFoundException;
import com.example.exception.SaleNotFoundException;
import com.example.repository.InventoryRepository;
import com.example.repository.ProductRepository;
import com.example.repository.SaleRepository;
import com.example.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public SaleRequest addSale(SaleRequest saleRequest) {
        Product product=productRepository.findById(
                saleRequest.getProductId())
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
        Inventory inventory =inventoryRepository.findById(saleRequest.getProductId())
                .orElseThrow(()->new InventoryNotFoundException("Inventory not found"));
        if (inventory.getQuantity() < saleRequest.getQuantity())
            throw new RuntimeException("Insufficient stock");
        inventory.setQuantity(
                inventory.getQuantity() - saleRequest.getQuantity()
        );
        inventoryRepository.save(inventory);
        Sale sale = Converter.dtoToEntity(
                saleRequest,
                product
        );

        return Converter.entityToDto(saleRepository.save(sale));
    }

    @Override
    public List<SaleRequest> getAllSales() {
        return saleRepository.findAll()
                .stream().map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SaleRequest getSaleById(Long id) {
        return saleRepository.findById(id)
                .map(Converter::entityToDto)
                .orElseThrow(()->new SaleNotFoundException("Sale not found"));
    }

    @Override
    public SaleRequest updateSale(Long id, SaleRequest saleRequest) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() ->
                        new SaleNotFoundException("Sale not found"));

        Product product = productRepository
                .findById(saleRequest.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        sale.setQuantity(saleRequest.getQuantity());
        sale.setTotalAmount(saleRequest.getTotalAmount());
        sale.setSaleDate(saleRequest.getSaleDate());
        sale.setProduct(product);
        return Converter.entityToDto(saleRepository.save(sale));
    }

    @Override
    public String deleteSale(Long id) {
       Sale sale= saleRepository.findById(id)
                .orElseThrow(()->new SaleNotFoundException("Sale not found"));
               saleRepository.delete(sale);
               return "Sale deleted successfully";
    }

    @Override
    public List<SaleRequest> getSalesByProduct(Long productId) {
        return saleRepository.findByProductId(productId)
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleRequest> getSalesByDate(LocalDate date) {
        return saleRepository.findBySaleDate(date)
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleRequest> getSalesByDateRange(LocalDate startDate, LocalDate endDate) {
        return saleRepository
                .findBySaleDateBetween(startDate, endDate)
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalRevenue() {

        return saleRepository.findAll()
                .stream()
                .mapToDouble(Sale::getTotalAmount)
                .sum();
    }

    @Override
    public List<SaleRequest> getTopSellingProducts() {
        return saleRepository.findAllByOrderByQuantityDesc()
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }
}
