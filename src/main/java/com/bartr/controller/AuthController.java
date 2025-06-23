package com.bartr.controller;

import com.bartr.model.User;
import com.bartr.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuthController {
    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody User user){
        Map<String,String> response = new HashMap<>();
        response.put("token", userService.jwtLogin(user));
//        response.put(, userService.jwtLogin(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyUsername(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> user = userService.getUserByUsername(username);
        return ResponseEntity.ok(username);
    }

}
