package com.ctrlaltdefeat.Bartr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrlaltdefeat.Bartr.models.Enrollment;
import com.ctrlaltdefeat.Bartr.services.EnrollmentService;

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
