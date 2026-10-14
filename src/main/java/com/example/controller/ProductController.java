package com.example.controller;

import com.example.dto.ProductRequest;
import com.example.dto.ProductResponse;
import com.example.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Add Product
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(
            @RequestBody ProductRequest productRequest) {

        return ResponseEntity.ok(
                productService.addProduct(productRequest)
        );
    }

    // Get All Products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    // Get Product By ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest) {

        return ResponseEntity.ok(
                productService.updateProduct(id, productRequest)
        );
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productService.deleteProduct(id)
        );
    }

    // Search By Name
    @GetMapping("/search/name")
    public ResponseEntity<List<ProductResponse>> searchByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                productService.searchProductsByName(name)
        );
    }

    // Search By Brand
    @GetMapping("/search/brand")
    public ResponseEntity<List<ProductResponse>> searchByBrand(
            @RequestParam String brand) {

        return ResponseEntity.ok(
                productService.searchProductsByBrand(brand)
        );
    }

    // Filter By Category
    @GetMapping("/filter/category")
    public ResponseEntity<List<ProductResponse>> filterByCategory(
            @RequestParam Long categoryId) {

        return ResponseEntity.ok(
                productService.filterProductsByCategory(categoryId)
        );
    }

    // Sort By Price
    @GetMapping("/sort/price")
    public ResponseEntity<List<ProductResponse>> sortByPrice() {

        return ResponseEntity.ok(
                productService.sortProductsByPrice()
        );
    }
}
