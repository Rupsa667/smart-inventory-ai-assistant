package com.example.controller;


import com.example.dto.CategoryRequest;
import com.example.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryRequest> addCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(categoryService.addCategory(categoryRequest));
    }
    @GetMapping
    public ResponseEntity<List<CategoryRequest>> getAllCategories() {

        return ResponseEntity.ok(
                categoryService.getAllCategories()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRequest> getCategoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoryService.getCategoryById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryRequest> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest categoryRequest) {

        return ResponseEntity.ok(
                categoryService.updateCategory(id, categoryRequest)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoryService.deleteCategory(id)
        );
    }
        @GetMapping("/search")
        public ResponseEntity<List<CategoryRequest>> searchCategories (
                @RequestParam String name){

            return ResponseEntity.ok(categoryService.searchCategories(name));
        }

        @GetMapping("/sort/name")
        public ResponseEntity<List<CategoryRequest>> sortCategoriesByName () {

            return ResponseEntity.ok(categoryService.sortCategoriesByName());
        }
    }
