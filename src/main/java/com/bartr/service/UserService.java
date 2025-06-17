package com.bartr.service;

import com.bartr.model.User;

import java.util.Optional;

public interface UserService {

    User registerUser(String email);

    Optional<User> getUserByEmail(String email);

    void updateXP(int userId, int xp);

    int getUserXp(int userId);

}
