package com.ctrlaltdefeat.Bartr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrlaltdefeat.Bartr.models.Category;
import com.ctrlaltdefeat.Bartr.services.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private final CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) throws AppwriteException{
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable String id) throws AppwriteException{
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<Category> getAllCategories() throws AppwriteException{
        return catgeoryService.getAllCategories();
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable String id, @RequestBody Category category) throws AppwriteException{
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable String id) throws AppwriteException{
        catgeoryService.deleteCategory(id);
    }



}



