package com.ctrlaltdefeat.Bartr.controllers;

import org.springframework.stereotype.Controller;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    @Autowired
    private final EnrollmentService enrollmentService;

    @PostMapping
    public Enrollment enrollUser(@RequestBody Enrollment enrollment) {

    }

}
