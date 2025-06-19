package com.bartr.controller;

import com.bartr.model.Course;
import com.bartr.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    //  Create a new course
    @PostMapping("/insertCourse")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course created = courseService.createCourse(course);
        return ResponseEntity.ok(created);
    }

    // Update existing course. and only the person having the token can only change the same person who is logged in.
    //No use write now no body would change course for now change via backend
    @PutMapping("updateCourse/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course course) {
        Course updated = courseService.updateCourse(id, course);
        return ResponseEntity.ok(updated);
    }

    // Delete a course only the creator can delete it
    //No option to delete a course for now. You can do iot only via backend or sql
    @DeleteMapping("deleteCourse/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully.");
    }

    // Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    // Get all courses
    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // Get courses by creator ID
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<Course>> getCoursesByCreator(@PathVariable int creatorId) {
        return ResponseEntity.ok(courseService.getCoursesByCreatorId(creatorId));
    }

    // Get courses by category ID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable int categoryId) {
        return ResponseEntity.ok(courseService.getCoursesByCategoryId(categoryId));
    }
}
