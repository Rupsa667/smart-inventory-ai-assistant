package com.example.service;

import com.example.dto.CategoryRequest;
import java.util.List;

public interface CategoryService {
    CategoryRequest addCategory(CategoryRequest categoryRequest);
    List<CategoryRequest>getAllCategories();
   CategoryRequest getCategoryById(Long id);
 CategoryRequest updateCategory(Long id,CategoryRequest categoryRequest);
 String deleteCategory(Long id);
    List<CategoryRequest> searchCategories(String name);

    List<CategoryRequest> sortCategoriesByName();
}
