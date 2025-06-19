package com.bartr.repository;

import com.bartr.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Add custom query methods if needed
    @Query("Select name from Category")
    List<String> findAllCategoryNames();
}
