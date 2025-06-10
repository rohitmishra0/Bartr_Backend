package com.ctrlaltdefeat.Bartr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.appwrite.exceptions.AppwriteException;

@Controller
public class CourseController {
    @Autowired
    private final CourseService courseService;

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseService.getCourse(course);
    }

    @GetMapoping("/{id}")
    public Course getCourseById(@PathVariable String id) throws AppwriteException{
        return courseService.getCourseById(id);
    }

    @GetMapping
    public List<Course> getAllCourse() throws AppwriteException{
        return courseService.getAllCourses();
    }

    @GetMapping("/category/{categoryId}")
    public Course getCourseById(@PathVariable String id) throws AppwriteException {
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable String categoryId) throws AppwriteException{
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable String id) throws AppwriteException{
        courseService.deleteCourse(id);
    }

}
