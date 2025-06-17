package com.bartr.service.impl;

import com.bartr.model.User;
import com.bartr.repository.UserRepository;
import com.bartr.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor injection (no need for @Autowired due to single constructor)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        // Check if user with same email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already registered with email: " + user.getEmail());
        }

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
}
