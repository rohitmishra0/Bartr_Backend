package com.ctrlaltdefeat.Bartr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.appwrite.exceptions.AppwriteException;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    @Autowired
    private final EnrollmentService enrollmentService;

    @PostMapping
    public Enrollment enrollUser(@RequestBody Enrollment enrollment) throws AppwriteException {
        return enrollmentService.enrollUser(enrollment);
    }

    @GetMapping("/user/{userId}")
    public List<Enrollment> getEnrollmentsForUser(@PathVariable String userId) throws AppwriteException{
        return enrollmentService.getEnrollmentsForUser(userId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsForCourse(@PathVariable String courseId) throws AppwriteException{
        return enrollmentService.getEnrollmentsForCourse(courseId);
    }

    @DeleteMapping("/id")
    public void deleteEnrollment(@PathVariable String id) throws AppwriteException{
        enrollmentService.deleteEnrollment(id);
    }


}
