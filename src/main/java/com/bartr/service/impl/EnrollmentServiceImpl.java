package com.bartr.service.impl;

import com.bartr.dao.CourseDao;
import com.bartr.dao.EnrollmentDao;
import com.bartr.dao.UserDao;
import com.bartr.model.Course;
import com.bartr.model.Enrollment;
import com.bartr.model.Transaction;
import com.bartr.model.User;
import com.bartr.repository.CourseRepository;
import com.bartr.repository.TransactionRepository;
import com.bartr.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository ;
    private final CourseRepository courseRepository;

    @Override
    public Enrollment enroll(int userId, int courseId) {
        User learner = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (learner.getXp() < course.getPrice()) {
            throw new RuntimeException("Insufficient XP to enroll");
        }

        // 🔻 Deduct XP from learner
        learner.setXp(learner.getXp() - (int) course.getPrice());
        userRepository.save(learner);

        // 🔻 Create learner's transaction ("sent")
        Transaction sentTxn = new Transaction();
        sentTxn.setUser(learner);
        sentTxn.setCourse(course);
        sentTxn.setType("sent");
        sentTxn.setAmount((int) course.getPrice());
        transactionRepository.save(sentTxn);

        // 🔻 Credit XP to course creator
        User creator = course.getCreator();
        creator.setXp(creator.getXp() + (int) course.getPrice());
        userRepository.save(creator);

        // 🔻 Create creator's transaction ("received")
        Transaction receivedTxn = new Transaction();
        receivedTxn.setUser(creator);
        receivedTxn.setCourse(course);
        receivedTxn.setType("received");
        receivedTxn.setAmount((int) course.getPrice());
        transactionRepository.save(receivedTxn);

        // 🔻 Save enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setLearner(learner);

        return enrollmentDao.save(enrollment);
    }


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
