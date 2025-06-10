package com.ctrlaltdefeat.Bartr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrlaltdefeat.Bartr.services.CourseService;
import com.ctrlaltdefeat.Bartr.models.Course;;



@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private final CourseService courseService;

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseService.getCourse(course);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

    @GetMapping
    public List<Course> getAllCourse() {
        return courseService.getAllCourses();
    }

    @GetMapping("/category/{categoryId}")
    public Course getCourseById(@PathVariable String id)  {
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable String categoryId){
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }

}
