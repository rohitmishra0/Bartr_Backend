package com.ctrlaltdefeat.Bartr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrlaltdefeat.Bartr.Services.UserService;
import com.ctrlaltdefeat.Bartr.models.User;

import io.appwrite.exceptions.AppwriteException;
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping    
    public User createUser(@RequestBody User user) throws AppwriteException{
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) throws AppwriteException{
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() throws AppwriteException{
        return userService.getAllUsers();
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) throws AppwriteException{
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) throws AppwriteException{
        userService.deleteUser(id);
    }
    
    
    
}
