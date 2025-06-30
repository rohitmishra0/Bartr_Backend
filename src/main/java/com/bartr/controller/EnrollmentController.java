package com.bartr.controller;

import com.bartr.model.Course;
import com.bartr.model.Enrollment;
import com.bartr.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/insert")
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.saveEnrollment(enrollment));
    }

    @PostMapping("/insert/{userId}/{courseId}")
    public ResponseEntity<Enrollment> enrollUser(@PathVariable int userId, @PathVariable int courseId) {
        return ResponseEntity.ok(enrollmentService.enroll(userId,courseId));
    }
    @GetMapping("")
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable int id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        return (enrollment != null) ? ResponseEntity.ok(enrollment) : ResponseEntity.notFound().build();
    }

    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByLearner(@PathVariable int learnerId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByLearnerId(learnerId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable int courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourseId(courseId));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable int id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{learnerId}/courses")
    public ResponseEntity<?> getEnrolledCoursesForLearner(@PathVariable int learnerId) {

            List<Course> courses = enrollmentService.getCoursesEnrolledByLearnerId(learnerId);
            if (courses.isEmpty()) {
                // If the learner exists but has no enrollments
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(courses);

    }

    @GetMapping("/isEnrolled")
    public ResponseEntity<?> isEnrolled(@RequestParam int learnerId, @RequestParam int courseId){
        System.out.println("sfsjdcbgydVCJFWgevdcgdfvxgfwVcxdfQVCWTDXCQWtjgjvnaj");
        boolean isEnrolled = enrollmentService.isUserEnrolled(learnerId,courseId);
        return ResponseEntity.ok(isEnrolled);
    }


}
