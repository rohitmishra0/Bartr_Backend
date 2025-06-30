package com.bartr.service;

import com.bartr.model.Course;
import com.bartr.model.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment enroll(int userId, int courseId);
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(int id);
    List<Enrollment> getEnrollmentsByLearnerId(int learnerId);
    List<Enrollment> getEnrollmentsByCourseId(int courseId);
    Enrollment saveEnrollment(Enrollment enrollment);
    void deleteEnrollment(int id);
    List<Course> getCoursesEnrolledByLearnerId(int learnerId);
    public boolean isUserEnrolled(int learnerId, int courseId);
}
