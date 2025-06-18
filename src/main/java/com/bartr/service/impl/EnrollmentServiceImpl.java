package com.bartr.service.impl;

import com.bartr.model.Enrollment;
import com.bartr.repository.EnrollmentRepository;
import com.bartr.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment insertEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteEnrollment(int enrollmentId) {
        enrollmentRepository.deleteById(enrollmentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByLearnerId(int learnerId) {
        return enrollmentRepository.findByLearnerId(learnerId);
    }

    @Override
    public List<Integer> getAllLearnerIdsByCourseId(int courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(enrollment -> enrollment.getLearner().getId())
                .collect(Collectors.toList());
    }
}
