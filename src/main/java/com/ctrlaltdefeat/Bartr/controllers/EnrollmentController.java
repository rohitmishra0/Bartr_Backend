package com.ctrlaltdefeat.Bartr.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctrlaltdefeat.Bartr.models.Enrollment;
import com.ctrlaltdefeat.Bartr.services.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    
    private final EnrollmentService enrollmentService;

    

   public EnrollmentController(EnrollmentService enrollmentService) {
       this.enrollmentService = enrollmentService;
   }

    @PostMapping
    public Enrollment enrollUser(@RequestParam String userId,@RequestParam String courseId) {
        return enrollmentService.enrollUser(userId,courseId);
    }

    @GetMapping("/user/{userId}")
    public List<Enrollment> getEnrollmentsForUser(@PathVariable int userId) {
        return enrollmentService.getEnrollmentsForUser(userId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsForCourse(@PathVariable int courseId) {
        return enrollmentService.getEnrollmentsForCourse(courseId);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable String id) {
        enrollmentService.deleteEnrollment(id);
    }


}
