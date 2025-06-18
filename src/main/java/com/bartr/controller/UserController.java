package com.bartr.controller;

import com.bartr.model.User;
import com.bartr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    // 🔹 Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("controller reached");
        User createdUser = userService.registerUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    // 🔹 Get user by email
    @GetMapping("/byEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Update XP for user
    @PutMapping("/updateXP")
    public ResponseEntity<String> updateUserXp(@RequestParam int userId, @RequestParam int xpChange) {
        userService.updateXP(userId, xpChange);
        return ResponseEntity.ok("XP updated successfully");
    }

    // 🔹 Get current XP for user
    @GetMapping("/{id}/xp")
    public ResponseEntity<Integer> getUserXp(@PathVariable("id") int userId) {
        int xp = userService.getUserXp(userId);
        return ResponseEntity.ok(xp);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

}
