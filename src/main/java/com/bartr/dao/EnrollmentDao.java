package com.bartr.dao;

import com.bartr.model.Course;
import com.bartr.model.Enrollment;
import com.bartr.model.User;
import com.bartr.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EnrollmentDao {

    private final EnrollmentRepository enrollmentRepository;

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> findById(int id) {
        return enrollmentRepository.findById(id);
    }

    public List<Enrollment> findByLearner(User learner) {
        return enrollmentRepository.findByLearner(learner);
    }

    public List<Enrollment> findByCourse(Course course) {
        return enrollmentRepository.findByCourse(course);
    }

    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteById(int id) {
        enrollmentRepository.deleteById(id);
    }
}
