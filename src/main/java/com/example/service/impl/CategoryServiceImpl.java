package com.example.service.impl;

import com.example.config.Converter;
import com.example.dto.CategoryRequest;
import com.example.entity.Category;
import com.example.exception.CategoryNotFoundException;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryRequest addCategory(CategoryRequest categoryRequest) {
        Category category=Converter.dtoToEntity(categoryRequest);
        Category save=categoryRepository.save(category);
        return Converter.entityToDto(save);
    }

    @Override
    public List<CategoryRequest> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryRequest getCategoryById(Long id) {
        return categoryRepository.findById(id).map(Converter::entityToDto)
                .orElseThrow(()->new CategoryNotFoundException("Category not found"));
    }

    @Override
    public CategoryRequest updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found"));
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        return Converter.entityToDto(categoryRepository.save(category));
    }

    @Override
    public String deleteCategory(Long id) {
        Category category =categoryRepository.findById(id)
                .orElseThrow(()->new CategoryNotFoundException("Category not found"));
        categoryRepository.delete(category);
        return "Category deleted successfully";
    }

    @Override
    public List<CategoryRequest> searchCategories(String name) {
    return categoryRepository.findByNameContainingIgnoreCase(name)
            .stream().map(Converter::entityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<CategoryRequest> sortCategoriesByName() {
        return categoryRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Converter::entityToDto)
                .collect(Collectors.toList());
    }
}
