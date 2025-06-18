package com.bartr.service;

import com.bartr.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Register a new user
    User registerUser(User user);

    // Get user by email
    Optional<User> getUserByEmail(String email);

    String jwtLogin(User user);


    public void updateXP(int userId, int xpChange);

    public int getUserXp(int userId);

    List<User> getAllUser();


}