package com.example.service.impl;

import com.example.config.Converter;
import com.example.dto.SupplierRequest;
import com.example.entity.Supplier;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.SupplierNotFoundException;
import com.example.repository.SupplierRepository;
import com.example.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    @Override
    public SupplierRequest addSupplier(SupplierRequest supplierRequest) {
        Supplier supplier=Converter.dtoToEntity(supplierRequest);
        Supplier sp=supplierRepository.save(supplier);
        return Converter.entityToDto(sp);
    }

    @Override
    public List<SupplierRequest> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream().map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierRequest getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .map(Converter::entityToDto)
                .orElseThrow(()-> new SupplierNotFoundException("Supplier not found"));
    }

    @Override
    public SupplierRequest updateSupplier(Long id, SupplierRequest supplierRequest) {
        Supplier supplier=supplierRepository.findById(id).orElseThrow(()->new SupplierNotFoundException("Supplier not found"));
        supplier.setName(supplierRequest.getName());
        supplier.setEmail(supplierRequest.getEmail());
        supplier.setPhone(supplierRequest.getPhone());
        supplier.setAddress(supplierRequest.getAddress());

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return Converter.entityToDto(updatedSupplier);
    }

    @Override
    public String deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new SupplierNotFoundException("Supplier not found"));

        supplierRepository.delete(supplier);

        return "Supplier deleted successfully";
    }

    @Override
    public List<SupplierRequest> searchSuppliersByName(String name) {
        return supplierRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierRequest> searchSuppliersByEmail(String email) {
        return supplierRepository.findByEmailContainingIgnoreCase(email)
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierRequest> sortSuppliersByName() {
        return supplierRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }
}
