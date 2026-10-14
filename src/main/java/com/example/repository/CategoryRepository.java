package com.example.repository;

import com.example.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category,Long> {

    List<Category> findByNameContainingIgnoreCase(String name);

    List<Category> findAllByOrderByNameAsc();
}
