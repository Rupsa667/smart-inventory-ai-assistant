package com.example.service;
import com.example.dto.ProductRequest;
import com.example.dto.ProductResponse;
import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    String deleteProduct(Long id);

    List<ProductResponse> searchProductsByName(String name);

    List<ProductResponse> searchProductsByBrand(String brand);

    List<ProductResponse> filterProductsByCategory(Long categoryId);

    List<ProductResponse> sortProductsByPrice();
}