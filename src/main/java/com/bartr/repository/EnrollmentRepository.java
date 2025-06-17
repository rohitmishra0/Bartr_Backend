package com.bartr.repository;

import com.bartr.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    // Add custom query methods if needed
    List<Enrollment> findByCourseId(int courseId);
    List<Enrollment> findByLearnerId(int learnerId);

}
