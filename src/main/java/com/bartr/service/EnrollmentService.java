package com.bartr.service;

import com.bartr.model.Enrollment;
import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(int id);
    List<Enrollment> getEnrollmentsByLearnerId(int learnerId);
    List<Enrollment> getEnrollmentsByCourseId(int courseId);
    Enrollment saveEnrollment(Enrollment enrollment);
    void deleteEnrollment(int id);
}
