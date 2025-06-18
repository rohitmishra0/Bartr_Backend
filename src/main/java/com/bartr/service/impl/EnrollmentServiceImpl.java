package com.bartr.service.impl;

import com.bartr.model.Course;
import com.bartr.model.Enrollment;
import com.bartr.model.User;
import com.bartr.repository.CourseRepository;
import com.bartr.repository.EnrollmentRepository;
import com.bartr.repository.UserRepository;
import com.bartr.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    //18July
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Enrollment insertEnrollment(Enrollment enrollment) {
        //18June
        int courseId = enrollment.getCourse().getId();
        int learnerId = enrollment.getLearner().getId();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));
        User learner = userRepository.findById(learnerId)
                .orElseThrow(() -> new RuntimeException("Learner not found: " + learnerId));

        enrollment.setCourse(course);
        enrollment.setLearner(learner);

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
