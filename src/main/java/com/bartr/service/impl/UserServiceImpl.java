package com.bartr.service.impl;

import com.bartr.model.User;
import com.bartr.repository.UserRepository;
import com.bartr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String email){

        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            throw new RuntimeException("User already registered");
        }

        User user = new User();
        user.setEmail(email);

        return userRepository.save(user);

    }

    @Override
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateXP(int userId, int xpChange){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setXp(user.getXp() + xpChange);
        userRepository.save(user);
    }

    @Override
    public int getUserXp(int userId){
        return userRepository.findById(userId)
                .map(User::getXp)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    

}
