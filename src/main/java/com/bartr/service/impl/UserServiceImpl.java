package com.bartr.service.impl;

import com.bartr.model.User;
import com.bartr.repository.UserRepository;
import com.bartr.security.JwtUtil;
import com.bartr.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final UserAuthServiceImpl userAuthService;
    private final JwtUtil jwt;

    @Override
    public User registerUser(User user) {
        // Check if user with same email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already registered with email: " + user.getEmail());
        }

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateXP(int userId, int xpChange) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.setXp(user.getXp() + xpChange);
        userRepository.save(user);
    }

    @Override
    public int getUserXp(int userId) {
        return userRepository.findById(userId)
                .map(User::getXp)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public String jwtLogin(User user) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));


            UserDetails userDetails = userAuthService.loadUserByUsername(user.getUsername());
            String token = jwt.generateToken(userDetails.getUsername(), userDetails.getAuthorities().toString());

            return token;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

public void updatePassword(String username, String newPassword) {
        try {
            User user = userRepository.findByUsername(username).orElse(null);

            if (user == null) {
                throw new Exception("User not found with the given name");
            } else {
                user.setPassword(encoder.encode(newPassword));
                userRepository.save(user);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
