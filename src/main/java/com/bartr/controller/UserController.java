package com.bartr.controller;

import com.bartr.model.Course;
import com.bartr.model.User;
import com.bartr.service.CourseService;
import com.bartr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("insert")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateCourse(id, user));
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Course> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }


}
