package com.bartr.service;

import com.bartr.model.Enrollment;

import java.util.List;

public interface EnrollmentService {
    Enrollment insertEnrollment(Enrollment enrollment);

    void deleteEnrollment(int enrollmentId);

    List<Enrollment> getEnrollmentsByCourseId(int courseId);

    List<Enrollment> getEnrollmentsByLearnerId(int learner);

    List<Integer> getAllLearnerIdsByCourseId(int courseId);
}
