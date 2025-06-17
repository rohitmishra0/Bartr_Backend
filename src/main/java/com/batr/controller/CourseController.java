package com.batr.controller;


import com.batr.model.Category;
import com.batr.model.Course;
import com.batr.service.CourseService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course created = courseService.createCourse(course);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course course) {
        Course updated = courseService.updateCourse(id,course);
        
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        Course course = courseService.getCourseById(id);
        
        return ResponseEntity.ok(course);
    }

  

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

   
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<Course>> getCoursesByCreator(@PathVariable int creatorId){
        List<Course> courses = courseService.getCoursesByCreatorId(creatorId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable int categoryId){
        List<Course> courses = courseService.getCoursesByCategoryId(categoryId);
        return ResponseEntity.ok(courses);
    }
}
