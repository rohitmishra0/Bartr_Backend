package com.bartr.controller;

import com.bartr.model.Course;
import com.bartr.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

//    @Autowired
//    private CourseService courseService;
//
//    @PostMapping("insert")
//    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
//        return ResponseEntity.status(201).body(courseService.createCourse(course));
//    }
//
//    @PutMapping("updateById/{id}")
//    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course course) {
//        return ResponseEntity.ok(courseService.updateCourse(id, course));
//    }
//
//    @DeleteMapping("deleteById/{id}")
//    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
//        courseService.deleteCourse(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("getById/{id}")
//    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
//        return ResponseEntity.ok(courseService.getCourseById(id));
//    }
//
//    @GetMapping("")
//    public ResponseEntity<List<Course>> getAllCourses() {
//        return ResponseEntity.ok(courseService.getAllCourses());
//    }
//
//    @GetMapping("/byCreator/{creatorId}")
//    public ResponseEntity<List<Course>> getByCreator(@PathVariable int creatorId) {
//        return ResponseEntity.ok(courseService.getCoursesByCreatorId(creatorId));
//    }
//
//    @GetMapping("/byCategory/{categoryId}")
//    public ResponseEntity<List<Course>> getByCategory(@PathVariable int categoryId) {
//        return ResponseEntity.ok(courseService.getCoursesByCategoryId(categoryId));
//    }
}
