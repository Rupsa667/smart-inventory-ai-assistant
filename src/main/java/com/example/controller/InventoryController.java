package com.example.controller;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;
import com.example.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> addInventory(
            @RequestBody InventoryRequest inventoryRequest) {
        return ResponseEntity.ok(
                inventoryService.addInventory(inventoryRequest)
        );
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        return ResponseEntity.ok(
                inventoryService.getAllInventory()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                inventoryService.getInventoryById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable Long id,
            @RequestBody InventoryRequest inventoryRequest) {
        return ResponseEntity.ok(
                inventoryService.updateInventory(id, inventoryRequest)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                inventoryService.deleteInventory(id)
        );
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryResponse>> getLowStockProducts() {
        return ResponseEntity.ok(
                inventoryService.getLowStockProducts()
        );
    }

    @GetMapping("/out-of-stock")
    public ResponseEntity<List<InventoryResponse>> getOutOfStockProducts() {
        return ResponseEntity.ok(
                inventoryService.getOutOfStockProducts()
        );
    }
}