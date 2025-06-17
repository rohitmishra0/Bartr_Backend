package com.bartr.controller;

import com.bartr.model.Enrollment;
import com.bartr.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("insert")
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment saved = enrollmentService.insertEnrollment(enrollment);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable int id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable int courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourseId(courseId));
    }

    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByLearner(@PathVariable int learnerId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByLearnerId(learnerId));
    }

    @GetMapping("/learner-ids/course/{courseId}")
    public ResponseEntity<List<Integer>> getLearnerIdsByCourse(@PathVariable int courseId) {
        return ResponseEntity.ok(enrollmentService.getAllLearnerIdsByCourseId(courseId));
    }
}
