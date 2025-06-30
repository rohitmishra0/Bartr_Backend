package com.bartr.service.impl;


import com.bartr.exception.UserAlreadyEnrolledException;
import com.bartr.model.Course;
import com.bartr.model.Enrollment;
import com.bartr.model.Transaction;
import com.bartr.model.User;
import com.bartr.repository.CourseRepository;
import com.bartr.repository.EnrollmentRepository;
import com.bartr.repository.TransactionRepository;
import com.bartr.repository.UserRepository;
import com.bartr.service.EnrollmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository ;

    @Override
    public Enrollment enroll(int userId, int courseId) {
        boolean isEnrolled = isUserEnrolled(userId,courseId);
        if(isEnrolled){
            throw new UserAlreadyEnrolledException("User is already enrolled into the course");
        }
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

        return enrollmentRepository.save(enrollment);
        course.setEnrolledUser(course.getEnrolledUser()+1);
        courseRepository.save(course);

        return enrollmentDao.save(enrollment);
    }


    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(int id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Enrollment> getEnrollmentsByLearnerId(int learnerId) {
        return userRepository.findById(learnerId)
                .map(enrollmentRepository::findByLearner)
                .orElse(List.of());
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        return courseRepository.findById(courseId)
                .map(enrollmentRepository::findByCourse)
                .orElse(List.of());
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteEnrollment(int id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Course> getCoursesEnrolledByLearnerId(int learnerId) {
        Optional<User> learner = userRepository.findById(learnerId);
        if (learner.isEmpty()) {
            throw new RuntimeException("Learner with ID " + learnerId + " not found.");
        }

        // Use the custom query from EnrollmentRepository to directly fetch courses
        return enrollmentRepository.findCoursesByLearnerId(learnerId);
    }

    public boolean isUserEnrolled(int learnerId, int courseId){
        System.out.println("sfsjvsjvnaj");
        return enrollmentRepository.existsByLearnerIdAndCourseId(learnerId,courseId);
    }




}
