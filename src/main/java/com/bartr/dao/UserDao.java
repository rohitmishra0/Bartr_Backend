package com.bartr.dao; // Changed package to `dao` as it's no longer an `impl` of an interface

import com.bartr.model.User;
import com.bartr.repository.UserRepository;
import lombok.AllArgsConstructor; // For constructor injection
import org.springframework.stereotype.Repository; // Appropriate annotation for DAO layer

import java.util.List;
import java.util.Optional;

@Repository // Indicates that this class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
@AllArgsConstructor // Lombok annotation for constructor injection of UserRepository
public class UserDao {

    private final UserRepository userRepository; // The DAO now uses the Repository

    // The methods will encapsulate the UserRepository calls

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    // This method could be in service, but if DAO directly handles specific updates, it's fine here too.
    public void updateXp(int userId, int xpChange) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setXp(user.getXp() + xpChange);
            userRepository.save(user);
        } else {
            // In a real app, throw a specific exception
            throw new RuntimeException("User with ID " + userId + " not found for XP update in DAO.");
        }
    }
}