package com.example.service.impl;

import com.example.config.Converter;
import com.example.dto.ProductRequest;
import com.example.dto.ProductResponse;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.entity.Supplier;
import com.example.exception.ProductNotFoundException;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.repository.SupplierRepository;
import com.example.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {

        Category category = categoryRepository
                .findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Supplier supplier = supplierRepository
                .findById(productRequest.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Product product = Converter.dtoToEntity(
                productRequest,
                category,
                supplier
        );

        Product savedProduct = productRepository.save(product);

        return Converter.entityToDto(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {

        return productRepository.findById(id)
                .map(Converter::entityToDto)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));
    }

    @Override
    public ProductResponse updateProduct(
            Long id,
            ProductRequest productRequest) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        Category category = categoryRepository
                .findById(productRequest.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        Supplier supplier = supplierRepository
                .findById(productRequest.getSupplierId())
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found"));

        product.setName(productRequest.getName());
        product.setBrand(productRequest.getBrand());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category);
        product.setSupplier(supplier);

        Product updatedProduct = productRepository.save(product);

        return Converter.entityToDto(updatedProduct);
    }

    @Override
    public String deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        productRepository.delete(product);

        return "Product deleted successfully";
    }

    @Override
    public List<ProductResponse> searchProductsByName(String name) {

        return productRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> searchProductsByBrand(String brand) {

        return productRepository
                .findByBrandContainingIgnoreCase(brand)
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> filterProductsByCategory(Long categoryId) {

        return productRepository
                .findByCategoryId(categoryId)
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> sortProductsByPrice() {

        return productRepository
                .findAllByOrderByPriceAsc()
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }
}
