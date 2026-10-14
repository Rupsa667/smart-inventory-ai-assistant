package com.example.service;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

    InventoryResponse addInventory(InventoryRequest inventoryRequest);

    List<InventoryResponse> getAllInventory();

    InventoryResponse getInventoryById(Long id);

    InventoryResponse updateInventory(Long id, InventoryRequest inventoryRequest);

    String deleteInventory(Long id);

    List<InventoryResponse> getLowStockProducts();

    List<InventoryResponse> getOutOfStockProducts();
}
