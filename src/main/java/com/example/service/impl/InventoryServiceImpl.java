package com.example.service.impl;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;
import com.example.entity.Inventory;
import com.example.entity.Product;
import com.example.exception.InventoryNotFoundException;
import com.example.repository.InventoryRepository;
import com.example.repository.ProductRepository;
import com.example.service.InventoryService;
import com.example.config.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Override
    public InventoryResponse addInventory(InventoryRequest inventoryRequest) {

        Product product = productRepository
                .findById(inventoryRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Inventory inventory = Converter.dtoToEntity(
                inventoryRequest,
                product
        );

        Inventory savedInventory = inventoryRepository.save(inventory);

        return Converter.entityToDto(savedInventory);
    }

    @Override
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponse getInventoryById(Long id) {

        return inventoryRepository.findById(id)
                .map(Converter::entityToDto)
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found"));
    }

    @Override
    public InventoryResponse updateInventory(
            Long id,
            InventoryRequest inventoryRequest) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found"));

        Product product = productRepository
                .findById(inventoryRequest.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        inventory.setQuantity(inventoryRequest.getQuantity());
        inventory.setReorderLevel(inventoryRequest.getReorderLevel());
        inventory.setProduct(product);

        Inventory updatedInventory = inventoryRepository.save(inventory);

        return Converter.entityToDto(updatedInventory);
    }

    @Override
    public String deleteInventory(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found"));

        inventoryRepository.delete(inventory);

        return "Inventory deleted successfully";
    }

    @Override
    public List<InventoryResponse> getLowStockProducts() {

        return inventoryRepository.findAll()
                .stream()
                .filter(inventory ->
                        inventory.getQuantity() <= inventory.getReorderLevel())
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryResponse> getOutOfStockProducts() {

        return inventoryRepository.findAll()
                .stream()
                .filter(inventory ->
                        inventory.getQuantity() == 0)
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }
}