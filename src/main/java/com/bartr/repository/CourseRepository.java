package com.bartr.repository;

import com.bartr.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // Add custom query methods if needed
    List<Course> findByCreatorId(int creatorId);
    List<Course> findByCategoryId(int categoryId);
}
