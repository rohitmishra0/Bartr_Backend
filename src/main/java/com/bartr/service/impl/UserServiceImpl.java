package com.bartr.service.impl;

import com.bartr.exception.InvalidPasswordException;
import com.bartr.exception.UserNameNotFoundException;
import com.bartr.exception.UsernameAlreadyExistsException;
import com.bartr.model.Role;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // Lombok provides constructor for all final fields
public class UserServiceImpl implements UserService {

    // Now inject the concrete userRepository instead of UserRepository
    private final UserRepository userRepository; // THIS IS THE KEY CHANGE
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final UserAuthServiceImpl userAuthService;
    private final JwtUtil jwt;

    @Override
    @Transactional
    public User registerUser(User user) {
        // Business logic remains in service, but persistence delegated to DAO
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new UsernameAlreadyExistsException("User already registered with email: " + user.getEmail());
        }

        Optional<User> existingUserByUsername = userRepository.findByUsername(user.getUsername());
        if (existingUserByUsername.isPresent()) {
            throw new UsernameAlreadyExistsException("User already registered with username: " + user.getUsername());
        }

        user.setPassword(encoder.encode(user.getPassword()));

        if (user.getXp() == 0) {
            user.setXp(0);
        }

//        user.setRole(Role.ROLE_USER);

        // Delegate save operation to userRepository
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        // Delegate find operation to userRepository
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void updateXP(int userId, int xpChange) {
        // Find user via DAO, then update and save via DAO
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.setXp(user.getXp() + xpChange);
        userRepository.save(user); // Delegate save for update
    }

    @Override
    public int getUserXp(int userId) {
        // Delegate find operation to userRepository
        return userRepository.findById(userId)
                .map(User::getXp)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public List<User> getAllUser() {
        // Delegate find all operation to userRepository
        return userRepository.findAll();
    }

    @Transactional
    public String jwtLogin(User user) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userAuthService.loadUserByUsername(user.getUsername());
            System.out.println(userDetails);
            String token = jwt.generateToken(userDetails.getUsername(), userDetails.getAuthorities().toString());
            return token;
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void updatePassword(String username, String newPassword) {
        // Find user via DAO, then update and save via DAO
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with the given username: " + username));

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user); // Delegate save for update
    }

    public User updateUser(int id, User updatedUser) throws UserNameNotFoundException {
        // Retrieve the existing user from the database
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNameNotFoundException("User not found with ID: " + id));

        // Only update mutable fields (immutable fields like 'username', 'email', etc., are excluded from form and ignored)
        if (updatedUser.getFullname() != null && !updatedUser.getFullname().isEmpty()) {
            existingUser.setFullname(updatedUser.getFullname());
        }
        if (updatedUser.getPhone() != null && !updatedUser.getPhone().isEmpty()) {
            existingUser.setPhone(updatedUser.getPhone());
        }
        if (updatedUser.getRegion() != null && !updatedUser.getRegion().isEmpty()) {
            existingUser.setRegion(updatedUser.getRegion());
        }
        if (updatedUser.getSkills() != null && !updatedUser.getSkills().isEmpty()) {
            existingUser.setSkills(updatedUser.getSkills());
        }
        if (updatedUser.getBio() != null && !updatedUser.getBio().isEmpty()) {
            existingUser.setBio(updatedUser.getBio());
        }



        // Save and return the updated user entity
        return userRepository.save(existingUser);
    }


    public boolean changePassword(int userId,String currentPassword,String newPassword){
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new UserNameNotFoundException("User not found with ID: " + userId));
        if (!encoder.matches(currentPassword,user.getPassword())){
            throw new InvalidPasswordException("Current password is incorrect");
        }

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }




}