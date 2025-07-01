package com.bartr.repository;

import com.bartr.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // Add custom query methods if needed
    List<Course> findByCreatorId(int creatorId);

    List<Course> findByCategoryId(int categoryId);

    @Query("""
            SELECT c FROM Course c
            JOIN c.category cat
            JOIN c.creator creator
            WHERE (
                LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                LOWER(cat.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                LOWER(creator.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            )
            """)
    List<Course> searchRelevantCourses(@Param("keyword") String keyword);

}
