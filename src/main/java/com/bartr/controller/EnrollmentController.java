package com.bartr.controller;

import com.bartr.model.Course;
import com.bartr.model.Enrollment;
import com.bartr.model.User;
import com.bartr.service.EnrollmentService;
import com.bartr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("insert")
    public ResponseEntity<User> createEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.status(201).body(enrollmentService.createEnrollment(enrollment));
    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<Course> updateEnrollment(@PathVariable int id, @RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(userService.updateEnrollment(id, enrollment));
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Course> getEnrollmentById(@PathVariable int id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }


}