package com.bartr.controller;

import com.bartr.model.User;
import com.bartr.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {
    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody User user){
        Map<String,String> response = new HashMap<>();
        response.put("token", userService.jwtLogin(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
