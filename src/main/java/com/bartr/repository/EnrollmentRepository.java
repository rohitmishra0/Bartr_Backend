package com.bartr.repository;

import com.bartr.model.Enrollment;
import com.bartr.model.User;
import com.bartr.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByLearner(User learner);
    List<Enrollment> findByCourse(Course course);

    @Query("SELECT e.course FROM Enrollment e WHERE e.learner.id = :learnerId")
    List<Course> findCoursesByLearnerId(@Param("learnerId") int learnerId);


    boolean existsByLearnerIdAndCourseId(int learnerId, int courseId);
    void deleteByCourse(Course course);
}
