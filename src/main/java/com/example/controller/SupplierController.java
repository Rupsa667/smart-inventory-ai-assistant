package com.example.controller;

import com.example.dto.SupplierRequest;
import com.example.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@AllArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierRequest> addSupplier(@RequestBody SupplierRequest supplierRequest) {
        return ResponseEntity.ok(
                supplierService.addSupplier(supplierRequest)
        );
    }

    @GetMapping
    public ResponseEntity<List<SupplierRequest>> getAllSuppliers() {

        return ResponseEntity.ok(
                supplierService.getAllSuppliers()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierRequest> getSupplierById(@PathVariable Long id) {

        return ResponseEntity.ok(
                supplierService.getSupplierById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierRequest> updateSupplier(
            @PathVariable Long id,
            @RequestBody SupplierRequest supplierRequest) {

        return ResponseEntity.ok(
                supplierService.updateSupplier(id, supplierRequest)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {

        return ResponseEntity.ok(
                supplierService.deleteSupplier(id)
        );
    }

    // Search by name
    @GetMapping("/search/name")
    public ResponseEntity<List<SupplierRequest>> searchByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                supplierService.searchSuppliersByName(name)
        );
    }

    // Search by email
    @GetMapping("/search/email")
    public ResponseEntity<List<SupplierRequest>> searchByEmail(
            @RequestParam String email) {

        return ResponseEntity.ok(
                supplierService.searchSuppliersByEmail(email)
        );
    }

    // Sort A-Z
    @GetMapping("/sort/name")
    public ResponseEntity<List<SupplierRequest>> sortByName() {

        return ResponseEntity.ok(
                supplierService.sortSuppliersByName()
        );
    }
}
