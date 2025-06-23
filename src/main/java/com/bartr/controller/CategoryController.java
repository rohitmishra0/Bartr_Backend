package com.bartr.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bartr.model.Category;
import com.bartr.service.CategoryService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //This should be accessible without Login Also.
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //Only admin will have access for that
    @PostMapping("insertCategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category created = categoryService.createCategory(category);
        return ResponseEntity.status(201).body(created);
    }

    //Only admin will have access for this
    @PutMapping("updateCategory/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable int categoryId, @RequestBody Category category) {
        Category updated = categoryService.updateCategory(categoryId,category);
        
        return ResponseEntity.ok(updated);
    }

    //Only admin will have access for this
    @DeleteMapping("deleteCategory/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int categoryId) {
        categoryService.deleteCategory(categoryId);
        
        return ResponseEntity.noContent().build();
    }

    //Accessible
    @GetMapping("getCategoryByID/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        
        return ResponseEntity.ok(category);
    }

    //This should be secured. Login Authenetication is required.
    @GetMapping("names")
    public ResponseEntity<List<String>> getAllCategoryNames() {
        List<String> categoryNames = categoryService.getAllCategoryNames();
        return ResponseEntity.ok(categoryNames);
    }

}
