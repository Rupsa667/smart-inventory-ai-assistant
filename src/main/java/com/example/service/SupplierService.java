package com.example.service;

import com.example.dto.SupplierRequest;

import java.util.List;

public interface SupplierService {
    SupplierRequest addSupplier(SupplierRequest supplierRequest);
    List<SupplierRequest> getAllSuppliers();
    SupplierRequest getSupplierById(Long id);
    SupplierRequest updateSupplier(Long id, SupplierRequest supplierRequest);
    String deleteSupplier(Long id);
    List<SupplierRequest> searchSuppliersByName(String name);
    List<SupplierRequest> searchSuppliersByEmail(String email);
    List<SupplierRequest> sortSuppliersByName();
}
