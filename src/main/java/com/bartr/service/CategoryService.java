package com.bartr.service;

import java.util.List;

import com.bartr.model.Category;

public interface CategoryService {

    Category createCategory(Category category);

    Category updateCategory(int id, Category category);

    void deleteCategory(int id);

    Category getCategoryById(int id);

    List<Category> getAllCategories();
}
