package com.ctrlaltdefeat.Bartr.controllers;

import org.springframework.stereotype.Controller;

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

7873209190
