package com.batr.service.impl;

import java.util.List;

import com.batr.model.Category;
import com.batr.repository.CategoryRepository;
import com.batr.service.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int id, Category category){
        Category existing = categoryRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Category not found with ID: "+id));
         existing.setName(category.getName());
         existing.setDescription(category.getDescription());
         existing.setXpCost(category.getXpCost());
         return categoryRepository.save(existing);                   
    }

    @Override
    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(int id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: "+id));
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

}
