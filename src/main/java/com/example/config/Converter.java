package com.example.config;

import com.example.dto.*;
import com.example.entity.*;

public class Converter {

    public static CategoryRequest entityToDto(Category category) {

        return new CategoryRequest(
                category.getName(),
                category.getDescription()
        );
    }

    public static Category dtoToEntity(CategoryRequest categoryRequest) {

        return new Category(
                categoryRequest.getName(),
                categoryRequest.getDescription()
        );
    }
    public static SupplierRequest entityToDto(Supplier supplier) {
        return new SupplierRequest(
                supplier.getName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getAddress()
        );
    }

    public static Supplier dtoToEntity(SupplierRequest dto) {
        return new Supplier(
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getAddress()
        );
    }
    public static ProductResponse entityToDto(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getSupplier().getId()
        );
    }

    // Product DTO -> Entity
    public static Product dtoToEntity(
            ProductRequest dto,
            Category category,
            Supplier supplier) {

        return new Product(
                dto.getName(),
                dto.getBrand(),
                dto.getPrice(),
                category,
                supplier
        );
    }
    public static InventoryResponse entityToDto(Inventory inventory) {

        return new InventoryResponse(
                inventory.getId(),
                inventory.getQuantity(),
                inventory.getReorderLevel(),
                inventory.getProduct().getId()
        );
    }
    public static Inventory dtoToEntity(
            InventoryRequest dto,
            Product product) {

        return new Inventory(
                dto.getQuantity(),
                dto.getReorderLevel(),
                product
        );
    }
    public static SaleRequest entityToDto(Sale sale){
        return new SaleRequest(
                sale.getQuantity(),
                sale.getTotalAmount(),
                sale.getSaleDate(),
                sale.getProduct().getId()
        );
    }
    public static Sale dtoToEntity(SaleRequest saleRequest,Product product){
        return new Sale(
                saleRequest.getQuantity(),
                saleRequest.getTotalAmount(),
                saleRequest.getSaleDate(),
                product
        );
    }
}
