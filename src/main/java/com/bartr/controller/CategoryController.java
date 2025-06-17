package com.bartr.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bartr.model.Category;
import com.bartr.service.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @PostMapping("insert/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category created = categoryService.createCategory(category);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
        Category updated = categoryService.updateCategory(id,category);
        
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(("category/deleteById/{id}"))
    public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("category/getByID/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Category category = categoryService.getCategoryById(id);
        
        return ResponseEntity.ok(category);
    }


}
