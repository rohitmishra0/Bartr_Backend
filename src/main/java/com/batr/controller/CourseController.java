package com.batr.controller;


import com.batr.model.Course;
import com.batr.service.CourseService;
import com.batr.service.impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course saved = courseServiceImpl.createCourse(course);
        return ResponseEntity.ok(saved);
    }

}
