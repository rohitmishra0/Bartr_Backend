package com.bartr.service.impl;

import com.bartr.dao.CourseDao;
import com.bartr.dao.EnrollmentDao;
import com.bartr.dao.UserDao;
import com.bartr.model.Course;
import com.bartr.model.Enrollment;
import com.bartr.model.User;
import com.bartr.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentDao enrollmentDao;
    private final UserDao userDao;
    private final CourseDao courseDao;

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDao.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(int id) {
        return enrollmentDao.findById(id).orElse(null);
    }

    @Override
    public List<Enrollment> getEnrollmentsByLearnerId(int learnerId) {
        return userDao.findById(learnerId)
                .map(enrollmentDao::findByLearner)
                .orElse(List.of());
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        return courseDao.findById(courseId)
                .map(enrollmentDao::findByCourse)
                .orElse(List.of());
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentDao.save(enrollment);
    }

    @Override
    public void deleteEnrollment(int id) {
        enrollmentDao.deleteById(id);
    }
}
