package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.User;
import com.ctrlaltdefeat.Bartr.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class UserService {
   private final UserRepository userRepository;
      private final ObjectMapper objectMapper =new ObjectMapper();

   public UserService(UserRepository userRepository) {
       this.userRepository = userRepository;
   }
   public User createUser(User user) {
    Map<String,Object> data = objectMapper.convertValue(user, Map.class);
       return userRepository.createDocument(data);
   }
   public User getUserById(String id) {
       return userRepository.getDocument(id);
   }
   public List<User> getAllUsers() {
       return userRepository.listDocuments();
   }
   public User updateUser(String id,User user) {
    Map<String,Object> data = objectMapper.convertValue(user, Map.class);
       return userRepository.updateDocument(id, data);
   }
   public void deleteUser(String id) {
        userRepository.deleteDocument(id);
   }
}