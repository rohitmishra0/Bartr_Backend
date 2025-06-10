package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.User;
import com.ctrlaltdefeat.Bartr.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class UserService {
   private final UserRepository userRepository;
      private final ObjectMapper objectMapper =new ObjectMapper();

   public UserService(UserRepository userRepository) {
       this.userRepository = userRepository;
   }
   public String createUser(User user) {
    Map<String,Object> data = objectMapper.convertValue(user, Map.class);
       return userRepository.createDocument(data);
   }
   public String getUserById(String id) {
       return userRepository.getDocument(id);
   }
   public String getAllUsers() {
       return userRepository.listDocuments();
   }
   public String updateUser(String id,User user) {
    Map<String,Object> data = objectMapper.convertValue(user, Map.class);
       return userRepository.updateDocument(id, data);
   }
   public String deleteUser(String id) {
       return userRepository.deleteDocument(id);
   }
}