package com.example.twitterclone.service;

import com.example.twitterclone.model.Category;
import com.example.twitterclone.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() { return categoryRepository.findAll(); }

    public Category saveCategory(Category category) { return categoryRepository.save(category); }

    public Category updateCategory(Long id, Category category) {
        return categoryRepository.findById(id)
                .map(c -> { c.setName(category.getName()); return categoryRepository.save(c); })
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteCategory(Long id) { categoryRepository.deleteById(id); }
}