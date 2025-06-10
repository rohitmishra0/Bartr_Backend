package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.Category;
import com.ctrlaltdefeat.Bartr.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CategoryService {
   private final CategoryRepository categoryRepository;
      private final ObjectMapper objectMapper =new ObjectMapper();

   public CategoryService(CategoryRepository categoryRepository) {
       this.categoryRepository = categoryRepository;
   }
   public Category createCategory(Category category) {
        Map<String,Object> data = objectMapper.convertValue(category, Map.class);
       return categoryRepository.createDocument(data);
   }
   public Category getCategoryById(String id) {
       return categoryRepository.getDocument(id);
   }
   public List<Category> getAllCategories() {
       return categoryRepository.listDocuments();
   }
   public Category updateCategory(String id, Category category) {
    Map<String,Object> data = objectMapper.convertValue(category, Map.class);

       return categoryRepository.updateDocument(id, data);
   }
   public void deleteCategory(String id) {
        categoryRepository.deleteDocument(id);
   }
}